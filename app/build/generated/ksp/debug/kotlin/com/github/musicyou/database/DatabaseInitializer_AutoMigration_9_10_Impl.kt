package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.room.util.foreignKeyCheck
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_9_10_Impl : Migration {
  public constructor() : super(9, 10)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongInPlaylist")
    connection.execSQL("ALTER TABLE `Artist` ADD COLUMN `shuffleVideoId` TEXT DEFAULT NULL")
    connection.execSQL("ALTER TABLE `Artist` ADD COLUMN `shufflePlaylistId` TEXT DEFAULT NULL")
    connection.execSQL("ALTER TABLE `Artist` ADD COLUMN `radioVideoId` TEXT DEFAULT NULL")
    connection.execSQL("ALTER TABLE `Artist` ADD COLUMN `radioPlaylistId` TEXT DEFAULT NULL")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `SongAlbumMap` (`songId` TEXT NOT NULL, `albumId` TEXT NOT NULL, `position` INTEGER, PRIMARY KEY(`songId`, `albumId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`albumId`) REFERENCES `Album`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongAlbumMap_songId` ON `SongAlbumMap` (`songId`)")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongAlbumMap_albumId` ON `SongAlbumMap` (`albumId`)")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `_new_Song` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `albumId` TEXT, `artistsText` TEXT, `durationText` TEXT NOT NULL, `thumbnailUrl` TEXT, `lyrics` TEXT, `likedAt` INTEGER, `totalPlayTimeMs` INTEGER NOT NULL, `loudnessDb` REAL, `contentLength` INTEGER, PRIMARY KEY(`id`), FOREIGN KEY(`albumId`) REFERENCES `Album`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )")
    connection.execSQL("INSERT INTO `_new_Song` (`id`,`title`,`albumId`,`artistsText`,`durationText`,`thumbnailUrl`,`lyrics`,`likedAt`,`totalPlayTimeMs`,`loudnessDb`,`contentLength`) SELECT `id`,`title`,`albumId`,`artistsText`,`durationText`,`thumbnailUrl`,`lyrics`,`likedAt`,`totalPlayTimeMs`,`loudnessDb`,`contentLength` FROM `Song`")
    connection.execSQL("DROP TABLE `Song`")
    connection.execSQL("ALTER TABLE `_new_Song` RENAME TO `Song`")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `_new_Album` (`id` TEXT NOT NULL, `title` TEXT, `thumbnailUrl` TEXT, `year` TEXT, `authorsText` TEXT, `shareUrl` TEXT, PRIMARY KEY(`id`))")
    connection.execSQL("INSERT INTO `_new_Album` (`id`,`title`,`thumbnailUrl`,`year`,`authorsText`) SELECT `id`,`title`,`thumbnailUrl`,`year`,`authorsText` FROM `Album`")
    connection.execSQL("DROP TABLE `Album`")
    connection.execSQL("ALTER TABLE `_new_Album` RENAME TO `Album`")
    foreignKeyCheck(connection, "Song")
    connection.execSQL("CREATE VIEW `SortedSongInPlaylist` AS SELECT * FROM SongInPlaylist ORDER BY position")
  }
}
