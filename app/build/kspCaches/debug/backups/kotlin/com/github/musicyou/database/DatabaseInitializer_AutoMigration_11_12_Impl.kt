package com.github.musicyou.database

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.foreignKeyCheck
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_11_12_Impl : Migration {
  private val callback: AutoMigrationSpec = DatabaseInitializer.From11To12Migration()

  public constructor() : super(11, 12)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongInPlaylist")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `_new_SongPlaylistMap` (`songId` TEXT NOT NULL, `playlistId` INTEGER NOT NULL, `position` INTEGER NOT NULL, PRIMARY KEY(`songId`, `playlistId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`playlistId`) REFERENCES `Playlist`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
    connection.execSQL("INSERT INTO `_new_SongPlaylistMap` (`songId`,`playlistId`,`position`) SELECT `songId`,`playlistId`,`position` FROM `SongInPlaylist`")
    connection.execSQL("DROP TABLE `SongInPlaylist`")
    connection.execSQL("ALTER TABLE `_new_SongPlaylistMap` RENAME TO `SongPlaylistMap`")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongPlaylistMap_songId` ON `SongPlaylistMap` (`songId`)")
    connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongPlaylistMap_playlistId` ON `SongPlaylistMap` (`playlistId`)")
    foreignKeyCheck(connection, "SongPlaylistMap")
    connection.execSQL("CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
    callback.onPostMigrate(connection)
  }
}
