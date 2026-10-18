package com.github.musicyou.database

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_7_8_Impl : Migration {
  private val callback: AutoMigrationSpec = DatabaseInitializer.From7To8Migration()

  public constructor() : super(7, 8)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongInPlaylist")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `_new_Song` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `albumId` TEXT, `artistsText` TEXT, `durationText` TEXT NOT NULL, `thumbnailUrl` TEXT, `lyrics` TEXT, `likedAt` INTEGER, `totalPlayTimeMs` INTEGER NOT NULL, `loudnessDb` REAL, `contentLength` INTEGER, PRIMARY KEY(`id`))")
    connection.execSQL("INSERT INTO `_new_Song` (`id`,`title`,`albumId`,`artistsText`,`durationText`,`thumbnailUrl`,`lyrics`,`likedAt`,`totalPlayTimeMs`,`loudnessDb`,`contentLength`) SELECT `id`,`title`,`albumInfoId`,`artistsText`,`durationText`,`thumbnailUrl`,`lyrics`,`likedAt`,`totalPlayTimeMs`,`loudnessDb`,`contentLength` FROM `Song`")
    connection.execSQL("DROP TABLE `Song`")
    connection.execSQL("ALTER TABLE `_new_Song` RENAME TO `Song`")
    connection.execSQL("CREATE VIEW `SortedSongInPlaylist` AS SELECT * FROM SongInPlaylist ORDER BY position")
    callback.onPostMigrate(connection)
  }
}
