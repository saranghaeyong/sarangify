package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_13_14_Impl : Migration {
  public constructor() : super(13, 14)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongPlaylistMap")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `Format` (`songId` TEXT NOT NULL, `itag` INTEGER, `mimeType` TEXT, `bitrate` INTEGER, `contentLength` INTEGER, `lastModified` INTEGER, `loudnessDb` REAL, PRIMARY KEY(`songId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
    connection.execSQL("CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
  }
}
