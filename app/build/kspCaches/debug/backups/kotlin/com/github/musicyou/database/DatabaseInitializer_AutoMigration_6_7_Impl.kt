package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_6_7_Impl : Migration {
  public constructor() : super(6, 7)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongInPlaylist")
    connection.execSQL("ALTER TABLE `Song` ADD COLUMN `artistsText` TEXT DEFAULT NULL")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `Album` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `thumbnailUrl` TEXT, `year` TEXT, `authorsText` TEXT, PRIMARY KEY(`id`))")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `Artist` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `thumbnailUrl` TEXT, `info` TEXT, PRIMARY KEY(`id`))")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `SongArtistMap` (`songId` TEXT NOT NULL, `artistId` TEXT NOT NULL, PRIMARY KEY(`songId`, `artistId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`artistId`) REFERENCES `Artist`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongArtistMap_songId` ON `SongArtistMap` (`songId`)")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongArtistMap_artistId` ON `SongArtistMap` (`artistId`)")
    connection.execSQL("CREATE VIEW `SortedSongInPlaylist` AS SELECT * FROM SongInPlaylist ORDER BY position")
  }
}
