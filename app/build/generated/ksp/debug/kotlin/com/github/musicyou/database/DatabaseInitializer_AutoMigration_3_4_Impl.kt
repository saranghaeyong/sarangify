package com.github.musicyou.database

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_3_4_Impl : Migration {
  private val callback: AutoMigrationSpec = DatabaseInitializer.From3To4Migration()

  public constructor() : super(3, 4)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongInPlaylist")
    connection.execSQL("DROP TABLE `QueuedMediaItem`")
    connection.execSQL("CREATE VIEW `SortedSongInPlaylist` AS SELECT * FROM SongInPlaylist ORDER BY position")
    callback.onPostMigrate(connection)
  }
}
