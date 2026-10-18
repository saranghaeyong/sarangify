package com.github.musicyou.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.ViewInfo
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass
import androidx.room.util.TableInfo.Companion.read as tableInfoRead
import androidx.room.util.ViewInfo.Companion.read as viewInfoRead

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class DatabaseInitializer_Impl : DatabaseInitializer() {
  private val _databaseDao: Lazy<DatabaseDao> = lazy {
    DatabaseDao_Impl(this)
  }

  public override val dao: DatabaseDao
    get() = _databaseDao.value

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(23, "205c24811149a247279bcbfdc2d6c396", "f5c46354272cf82d45838c429ec13efa") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Song` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `artistsText` TEXT, `durationText` TEXT, `thumbnailUrl` TEXT, `likedAt` INTEGER, `totalPlayTimeMs` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `SongPlaylistMap` (`songId` TEXT NOT NULL, `playlistId` INTEGER NOT NULL, `position` INTEGER NOT NULL, PRIMARY KEY(`songId`, `playlistId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`playlistId`) REFERENCES `Playlist`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongPlaylistMap_songId` ON `SongPlaylistMap` (`songId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongPlaylistMap_playlistId` ON `SongPlaylistMap` (`playlistId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Playlist` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `browseId` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Artist` (`id` TEXT NOT NULL, `name` TEXT, `thumbnailUrl` TEXT, `timestamp` INTEGER, `bookmarkedAt` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `SongArtistMap` (`songId` TEXT NOT NULL, `artistId` TEXT NOT NULL, PRIMARY KEY(`songId`, `artistId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`artistId`) REFERENCES `Artist`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongArtistMap_songId` ON `SongArtistMap` (`songId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongArtistMap_artistId` ON `SongArtistMap` (`artistId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Album` (`id` TEXT NOT NULL, `title` TEXT, `thumbnailUrl` TEXT, `year` TEXT, `authorsText` TEXT, `shareUrl` TEXT, `timestamp` INTEGER, `bookmarkedAt` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `SongAlbumMap` (`songId` TEXT NOT NULL, `albumId` TEXT NOT NULL, `position` INTEGER, PRIMARY KEY(`songId`, `albumId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`albumId`) REFERENCES `Album`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongAlbumMap_songId` ON `SongAlbumMap` (`songId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_SongAlbumMap_albumId` ON `SongAlbumMap` (`albumId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `SearchQuery` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `query` TEXT NOT NULL)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_SearchQuery_query` ON `SearchQuery` (`query`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `QueuedMediaItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `mediaItem` BLOB NOT NULL, `position` INTEGER)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Format` (`songId` TEXT NOT NULL, `itag` INTEGER, `mimeType` TEXT, `bitrate` INTEGER, `contentLength` INTEGER, `lastModified` INTEGER, `loudnessDb` REAL, PRIMARY KEY(`songId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Event` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `songId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `playTime` INTEGER NOT NULL, FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_Event_songId` ON `Event` (`songId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Lyrics` (`songId` TEXT NOT NULL, `fixed` TEXT, `synced` TEXT, PRIMARY KEY(`songId`), FOREIGN KEY(`songId`) REFERENCES `Song`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '205c24811149a247279bcbfdc2d6c396')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `Song`")
        connection.execSQL("DROP TABLE IF EXISTS `SongPlaylistMap`")
        connection.execSQL("DROP TABLE IF EXISTS `Playlist`")
        connection.execSQL("DROP TABLE IF EXISTS `Artist`")
        connection.execSQL("DROP TABLE IF EXISTS `SongArtistMap`")
        connection.execSQL("DROP TABLE IF EXISTS `Album`")
        connection.execSQL("DROP TABLE IF EXISTS `SongAlbumMap`")
        connection.execSQL("DROP TABLE IF EXISTS `SearchQuery`")
        connection.execSQL("DROP TABLE IF EXISTS `QueuedMediaItem`")
        connection.execSQL("DROP TABLE IF EXISTS `Format`")
        connection.execSQL("DROP TABLE IF EXISTS `Event`")
        connection.execSQL("DROP TABLE IF EXISTS `Lyrics`")
        connection.execSQL("DROP VIEW IF EXISTS `SortedSongPlaylistMap`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsSong: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSong.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSong.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSong.put("artistsText", TableInfo.Column("artistsText", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSong.put("durationText", TableInfo.Column("durationText", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSong.put("thumbnailUrl", TableInfo.Column("thumbnailUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSong.put("likedAt", TableInfo.Column("likedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSong.put("totalPlayTimeMs", TableInfo.Column("totalPlayTimeMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSong: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSong: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSong: TableInfo = TableInfo("Song", _columnsSong, _foreignKeysSong, _indicesSong)
        val _existingSong: TableInfo = tableInfoRead(connection, "Song")
        if (!_infoSong.equals(_existingSong)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Song(com.github.musicyou.models.Song).
              | Expected:
              |""".trimMargin() + _infoSong + """
              |
              | Found:
              |""".trimMargin() + _existingSong)
        }
        val _columnsSongPlaylistMap: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSongPlaylistMap.put("songId", TableInfo.Column("songId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSongPlaylistMap.put("playlistId", TableInfo.Column("playlistId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSongPlaylistMap.put("position", TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSongPlaylistMap: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSongPlaylistMap.add(TableInfo.ForeignKey("Song", "CASCADE", "NO ACTION", listOf("songId"), listOf("id")))
        _foreignKeysSongPlaylistMap.add(TableInfo.ForeignKey("Playlist", "CASCADE", "NO ACTION", listOf("playlistId"), listOf("id")))
        val _indicesSongPlaylistMap: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSongPlaylistMap.add(TableInfo.Index("index_SongPlaylistMap_songId", false, listOf("songId"), listOf("ASC")))
        _indicesSongPlaylistMap.add(TableInfo.Index("index_SongPlaylistMap_playlistId", false, listOf("playlistId"), listOf("ASC")))
        val _infoSongPlaylistMap: TableInfo = TableInfo("SongPlaylistMap", _columnsSongPlaylistMap, _foreignKeysSongPlaylistMap, _indicesSongPlaylistMap)
        val _existingSongPlaylistMap: TableInfo = tableInfoRead(connection, "SongPlaylistMap")
        if (!_infoSongPlaylistMap.equals(_existingSongPlaylistMap)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SongPlaylistMap(com.github.musicyou.models.SongPlaylistMap).
              | Expected:
              |""".trimMargin() + _infoSongPlaylistMap + """
              |
              | Found:
              |""".trimMargin() + _existingSongPlaylistMap)
        }
        val _columnsPlaylist: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaylist.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylist.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylist.put("browseId", TableInfo.Column("browseId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaylist: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlaylist: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPlaylist: TableInfo = TableInfo("Playlist", _columnsPlaylist, _foreignKeysPlaylist, _indicesPlaylist)
        val _existingPlaylist: TableInfo = tableInfoRead(connection, "Playlist")
        if (!_infoPlaylist.equals(_existingPlaylist)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Playlist(com.github.musicyou.models.Playlist).
              | Expected:
              |""".trimMargin() + _infoPlaylist + """
              |
              | Found:
              |""".trimMargin() + _existingPlaylist)
        }
        val _columnsArtist: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsArtist.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArtist.put("name", TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArtist.put("thumbnailUrl", TableInfo.Column("thumbnailUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArtist.put("timestamp", TableInfo.Column("timestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArtist.put("bookmarkedAt", TableInfo.Column("bookmarkedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysArtist: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesArtist: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoArtist: TableInfo = TableInfo("Artist", _columnsArtist, _foreignKeysArtist, _indicesArtist)
        val _existingArtist: TableInfo = tableInfoRead(connection, "Artist")
        if (!_infoArtist.equals(_existingArtist)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Artist(com.github.musicyou.models.Artist).
              | Expected:
              |""".trimMargin() + _infoArtist + """
              |
              | Found:
              |""".trimMargin() + _existingArtist)
        }
        val _columnsSongArtistMap: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSongArtistMap.put("songId", TableInfo.Column("songId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSongArtistMap.put("artistId", TableInfo.Column("artistId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSongArtistMap: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSongArtistMap.add(TableInfo.ForeignKey("Song", "CASCADE", "NO ACTION", listOf("songId"), listOf("id")))
        _foreignKeysSongArtistMap.add(TableInfo.ForeignKey("Artist", "CASCADE", "NO ACTION", listOf("artistId"), listOf("id")))
        val _indicesSongArtistMap: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSongArtistMap.add(TableInfo.Index("index_SongArtistMap_songId", false, listOf("songId"), listOf("ASC")))
        _indicesSongArtistMap.add(TableInfo.Index("index_SongArtistMap_artistId", false, listOf("artistId"), listOf("ASC")))
        val _infoSongArtistMap: TableInfo = TableInfo("SongArtistMap", _columnsSongArtistMap, _foreignKeysSongArtistMap, _indicesSongArtistMap)
        val _existingSongArtistMap: TableInfo = tableInfoRead(connection, "SongArtistMap")
        if (!_infoSongArtistMap.equals(_existingSongArtistMap)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SongArtistMap(com.github.musicyou.models.SongArtistMap).
              | Expected:
              |""".trimMargin() + _infoSongArtistMap + """
              |
              | Found:
              |""".trimMargin() + _existingSongArtistMap)
        }
        val _columnsAlbum: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAlbum.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbum.put("title", TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbum.put("thumbnailUrl", TableInfo.Column("thumbnailUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbum.put("year", TableInfo.Column("year", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbum.put("authorsText", TableInfo.Column("authorsText", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbum.put("shareUrl", TableInfo.Column("shareUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbum.put("timestamp", TableInfo.Column("timestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbum.put("bookmarkedAt", TableInfo.Column("bookmarkedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAlbum: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAlbum: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAlbum: TableInfo = TableInfo("Album", _columnsAlbum, _foreignKeysAlbum, _indicesAlbum)
        val _existingAlbum: TableInfo = tableInfoRead(connection, "Album")
        if (!_infoAlbum.equals(_existingAlbum)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Album(com.github.musicyou.models.Album).
              | Expected:
              |""".trimMargin() + _infoAlbum + """
              |
              | Found:
              |""".trimMargin() + _existingAlbum)
        }
        val _columnsSongAlbumMap: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSongAlbumMap.put("songId", TableInfo.Column("songId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSongAlbumMap.put("albumId", TableInfo.Column("albumId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSongAlbumMap.put("position", TableInfo.Column("position", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSongAlbumMap: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSongAlbumMap.add(TableInfo.ForeignKey("Song", "CASCADE", "NO ACTION", listOf("songId"), listOf("id")))
        _foreignKeysSongAlbumMap.add(TableInfo.ForeignKey("Album", "CASCADE", "NO ACTION", listOf("albumId"), listOf("id")))
        val _indicesSongAlbumMap: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSongAlbumMap.add(TableInfo.Index("index_SongAlbumMap_songId", false, listOf("songId"), listOf("ASC")))
        _indicesSongAlbumMap.add(TableInfo.Index("index_SongAlbumMap_albumId", false, listOf("albumId"), listOf("ASC")))
        val _infoSongAlbumMap: TableInfo = TableInfo("SongAlbumMap", _columnsSongAlbumMap, _foreignKeysSongAlbumMap, _indicesSongAlbumMap)
        val _existingSongAlbumMap: TableInfo = tableInfoRead(connection, "SongAlbumMap")
        if (!_infoSongAlbumMap.equals(_existingSongAlbumMap)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SongAlbumMap(com.github.musicyou.models.SongAlbumMap).
              | Expected:
              |""".trimMargin() + _infoSongAlbumMap + """
              |
              | Found:
              |""".trimMargin() + _existingSongAlbumMap)
        }
        val _columnsSearchQuery: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSearchQuery.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSearchQuery.put("query", TableInfo.Column("query", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSearchQuery: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSearchQuery: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSearchQuery.add(TableInfo.Index("index_SearchQuery_query", true, listOf("query"), listOf("ASC")))
        val _infoSearchQuery: TableInfo = TableInfo("SearchQuery", _columnsSearchQuery, _foreignKeysSearchQuery, _indicesSearchQuery)
        val _existingSearchQuery: TableInfo = tableInfoRead(connection, "SearchQuery")
        if (!_infoSearchQuery.equals(_existingSearchQuery)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SearchQuery(com.github.musicyou.models.SearchQuery).
              | Expected:
              |""".trimMargin() + _infoSearchQuery + """
              |
              | Found:
              |""".trimMargin() + _existingSearchQuery)
        }
        val _columnsQueuedMediaItem: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsQueuedMediaItem.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQueuedMediaItem.put("mediaItem", TableInfo.Column("mediaItem", "BLOB", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsQueuedMediaItem.put("position", TableInfo.Column("position", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysQueuedMediaItem: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesQueuedMediaItem: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoQueuedMediaItem: TableInfo = TableInfo("QueuedMediaItem", _columnsQueuedMediaItem, _foreignKeysQueuedMediaItem, _indicesQueuedMediaItem)
        val _existingQueuedMediaItem: TableInfo = tableInfoRead(connection, "QueuedMediaItem")
        if (!_infoQueuedMediaItem.equals(_existingQueuedMediaItem)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |QueuedMediaItem(com.github.musicyou.models.QueuedMediaItem).
              | Expected:
              |""".trimMargin() + _infoQueuedMediaItem + """
              |
              | Found:
              |""".trimMargin() + _existingQueuedMediaItem)
        }
        val _columnsFormat: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFormat.put("songId", TableInfo.Column("songId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFormat.put("itag", TableInfo.Column("itag", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFormat.put("mimeType", TableInfo.Column("mimeType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFormat.put("bitrate", TableInfo.Column("bitrate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFormat.put("contentLength", TableInfo.Column("contentLength", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFormat.put("lastModified", TableInfo.Column("lastModified", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFormat.put("loudnessDb", TableInfo.Column("loudnessDb", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFormat: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysFormat.add(TableInfo.ForeignKey("Song", "CASCADE", "NO ACTION", listOf("songId"), listOf("id")))
        val _indicesFormat: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoFormat: TableInfo = TableInfo("Format", _columnsFormat, _foreignKeysFormat, _indicesFormat)
        val _existingFormat: TableInfo = tableInfoRead(connection, "Format")
        if (!_infoFormat.equals(_existingFormat)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Format(com.github.musicyou.models.Format).
              | Expected:
              |""".trimMargin() + _infoFormat + """
              |
              | Found:
              |""".trimMargin() + _existingFormat)
        }
        val _columnsEvent: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEvent.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvent.put("songId", TableInfo.Column("songId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvent.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvent.put("playTime", TableInfo.Column("playTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEvent: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysEvent.add(TableInfo.ForeignKey("Song", "CASCADE", "NO ACTION", listOf("songId"), listOf("id")))
        val _indicesEvent: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesEvent.add(TableInfo.Index("index_Event_songId", false, listOf("songId"), listOf("ASC")))
        val _infoEvent: TableInfo = TableInfo("Event", _columnsEvent, _foreignKeysEvent, _indicesEvent)
        val _existingEvent: TableInfo = tableInfoRead(connection, "Event")
        if (!_infoEvent.equals(_existingEvent)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Event(com.github.musicyou.models.Event).
              | Expected:
              |""".trimMargin() + _infoEvent + """
              |
              | Found:
              |""".trimMargin() + _existingEvent)
        }
        val _columnsLyrics: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLyrics.put("songId", TableInfo.Column("songId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLyrics.put("fixed", TableInfo.Column("fixed", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLyrics.put("synced", TableInfo.Column("synced", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLyrics: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysLyrics.add(TableInfo.ForeignKey("Song", "CASCADE", "NO ACTION", listOf("songId"), listOf("id")))
        val _indicesLyrics: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLyrics: TableInfo = TableInfo("Lyrics", _columnsLyrics, _foreignKeysLyrics, _indicesLyrics)
        val _existingLyrics: TableInfo = tableInfoRead(connection, "Lyrics")
        if (!_infoLyrics.equals(_existingLyrics)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Lyrics(com.github.musicyou.models.Lyrics).
              | Expected:
              |""".trimMargin() + _infoLyrics + """
              |
              | Found:
              |""".trimMargin() + _existingLyrics)
        }
        val _infoSortedSongPlaylistMap: ViewInfo = ViewInfo("SortedSongPlaylistMap", "CREATE VIEW `SortedSongPlaylistMap` AS SELECT * FROM SongPlaylistMap ORDER BY position")
        val _existingSortedSongPlaylistMap: ViewInfo = viewInfoRead(connection, "SortedSongPlaylistMap")
        if (!_infoSortedSongPlaylistMap.equals(_existingSortedSongPlaylistMap)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SortedSongPlaylistMap(com.github.musicyou.models.SortedSongPlaylistMap).
              | Expected:
              |""".trimMargin() + _infoSortedSongPlaylistMap + """
              |
              | Found:
              |""".trimMargin() + _existingSortedSongPlaylistMap)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    val _tables: MutableSet<String> = mutableSetOf()
    _tables.add("SongPlaylistMap")
    _viewTables.put("sortedsongplaylistmap", _tables)
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "Song", "SongPlaylistMap", "Playlist", "Artist", "SongArtistMap", "Album", "SongAlbumMap", "SearchQuery", "QueuedMediaItem", "Format", "Event", "Lyrics")
  }

  public override fun clearAllTables() {
    super.performClear(true, "Song", "SongPlaylistMap", "Playlist", "Artist", "SongArtistMap", "Album", "SongAlbumMap", "SearchQuery", "QueuedMediaItem", "Format", "Event", "Lyrics")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(DatabaseDao::class, DatabaseDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    _autoMigrations.add(DatabaseInitializer_AutoMigration_1_2_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_2_3_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_3_4_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_4_5_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_5_6_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_6_7_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_7_8_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_9_10_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_11_12_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_12_13_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_13_14_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_15_16_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_16_17_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_17_18_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_18_19_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_19_20_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_20_21_Impl())
    _autoMigrations.add(DatabaseInitializer_AutoMigration_21_22_Impl())
    return _autoMigrations
  }
}
