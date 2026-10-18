package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_18_19_Impl : Migration {
  public constructor() : super(18, 19)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongPlaylistMap")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `Event` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `songId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `playTime` INTEGER NOT NULL, FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_Event_songId` ON `Event` (`songId`)")
    connection.execSQL("CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
  }
}
