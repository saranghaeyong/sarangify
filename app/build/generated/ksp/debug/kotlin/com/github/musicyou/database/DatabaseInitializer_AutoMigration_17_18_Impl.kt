package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_17_18_Impl : Migration {
  public constructor() : super(17, 18)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongPlaylistMap")
    connection.execSQL("ALTER TABLE `Artist` ADD COLUMN `bookmarkedAt` INTEGER DEFAULT NULL")
    connection.execSQL("ALTER TABLE `Album` ADD COLUMN `bookmarkedAt` INTEGER DEFAULT NULL")
    connection.execSQL("CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
  }
}
