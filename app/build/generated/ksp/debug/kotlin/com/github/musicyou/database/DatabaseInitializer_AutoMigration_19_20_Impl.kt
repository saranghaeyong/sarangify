package com.github.musicyou.database

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class DatabaseInitializer_AutoMigration_19_20_Impl : Migration {
  public constructor() : super(19, 20)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("DROP VIEW SortedSongPlaylistMap")
    connection.execSQL("CREATE TABLE IF NOT EXISTS `_new_Song` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `artistsText` TEXT, `durationText` TEXT, `thumbnailUrl` TEXT, `lyrics` TEXT, `synchronizedLyrics` TEXT, `likedAt` INTEGER, `totalPlayTimeMs` INTEGER NOT NULL, PRIMARY KEY(`id`))")
    connection.execSQL("INSERT INTO `_new_Song` (`id`,`title`,`artistsText`,`durationText`,`thumbnailUrl`,`lyrics`,`synchronizedLyrics`,`likedAt`,`totalPlayTimeMs`) SELECT `id`,`title`,`artistsText`,`durationText`,`thumbnailUrl`,`lyrics`,`synchronizedLyrics`,`likedAt`,`totalPlayTimeMs` FROM `Song`")
    connection.execSQL("DROP TABLE `Song`")
    connection.execSQL("ALTER TABLE `_new_Song` RENAME TO `Song`")
    connection.execSQL("CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
  }
}
