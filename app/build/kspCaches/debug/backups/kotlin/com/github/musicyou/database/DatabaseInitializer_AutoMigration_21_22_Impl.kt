package com.github.musicyou.database

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_21_22_Impl : Migration {
  private val callback: AutoMigrationSpec = DatabaseInitializer.From21To22Migration()

  public constructor() : super(21, 22)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongPlaylistMap")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `_new_Artist` (`id` TEXT NOT NULL, `name` TEXT, `thumbnailUrl` TEXT, `timestamp` INTEGER, `bookmarkedAt` INTEGER, PRIMARY KEY(`id`))")
    connection.execSQL("INSERT INTO `_new_Artist` (`id`,`name`,`thumbnailUrl`,`timestamp`,`bookmarkedAt`) SELECT `id`,`name`,`thumbnailUrl`,`timestamp`,`bookmarkedAt` FROM `Artist`")
    connection.execSQL("DROP TABLE `Artist`")
    connection.execSQL("ALTER TABLE `_new_Artist` RENAME TO `Artist`")
    connection.execSQL("CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
    callback.onPostMigrate(connection)
  }
}
