package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_5_6_Impl : Migration {
  public constructor() : super(5, 6)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongInPlaylist")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `QueuedMediaItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `mediaItem` BLOB NOT NULL, `position` INTEGER)")
    connection.execSQL("CREATE VIEW `SortedSongInPlaylist` AS SELECT * FROM SongInPlaylist ORDER BY position")
  }
}
