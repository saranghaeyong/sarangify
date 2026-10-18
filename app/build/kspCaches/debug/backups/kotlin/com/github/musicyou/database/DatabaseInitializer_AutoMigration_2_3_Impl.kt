package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_2_3_Impl : Migration {
  public constructor() : super(2, 3)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongInPlaylist")
    connection.execSQL("ALTER TABLE `Song` ADD COLUMN `lyrics` TEXT DEFAULT NULL")
    connection.execSQL("CREATE VIEW `SortedSongInPlaylist` AS SELECT * FROM SongInPlaylist ORDER BY position")
  }
}
