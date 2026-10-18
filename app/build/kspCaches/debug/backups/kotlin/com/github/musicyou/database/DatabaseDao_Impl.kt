package com.github.musicyou.database

import androidx.collection.LongSparseArray
import androidx.media3.common.MediaItem
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.RoomSQLiteQuery
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performBlocking
import androidx.room.util.recursiveFetchLongSparseArray
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import androidx.sqlite.db.SupportSQLiteQuery
import com.github.musicyou.models.Album
import com.github.musicyou.models.Artist
import com.github.musicyou.models.Event
import com.github.musicyou.models.Format
import com.github.musicyou.models.Info
import com.github.musicyou.models.Lyrics
import com.github.musicyou.models.Playlist
import com.github.musicyou.models.PlaylistPreview
import com.github.musicyou.models.PlaylistWithSongs
import com.github.musicyou.models.QueuedMediaItem
import com.github.musicyou.models.SearchQuery
import com.github.musicyou.models.Song
import com.github.musicyou.models.SongAlbumMap
import com.github.musicyou.models.SongArtistMap
import com.github.musicyou.models.SongPlaylistMap
import com.github.musicyou.models.SongWithContentLength
import javax.`annotation`.processing.Generated
import kotlin.ByteArray
import kotlin.Float
import kotlin.Function1
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class DatabaseDao_Impl(
  __db: RoomDatabase,
) : DatabaseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEvent: EntityInsertAdapter<Event>

  private val __insertAdapterOfFormat: EntityInsertAdapter<Format>

  private val __insertAdapterOfSearchQuery: EntityInsertAdapter<SearchQuery>

  private val __insertAdapterOfPlaylist: EntityInsertAdapter<Playlist>

  private val __insertAdapterOfSongPlaylistMap: EntityInsertAdapter<SongPlaylistMap>

  private val __insertAdapterOfSongArtistMap: EntityInsertAdapter<SongArtistMap>

  private val __insertAdapterOfSong: EntityInsertAdapter<Song>

  private val __insertAdapterOfQueuedMediaItem: EntityInsertAdapter<QueuedMediaItem>

  private val __insertAdapterOfAlbum: EntityInsertAdapter<Album>

  private val __insertAdapterOfSongAlbumMap: EntityInsertAdapter<SongAlbumMap>

  private val __insertAdapterOfArtist: EntityInsertAdapter<Artist>

  private val __insertAdapterOfSongArtistMap_1: EntityInsertAdapter<SongArtistMap>

  private val __deleteAdapterOfSearchQuery: EntityDeleteOrUpdateAdapter<SearchQuery>

  private val __deleteAdapterOfPlaylist: EntityDeleteOrUpdateAdapter<Playlist>

  private val __deleteAdapterOfSongPlaylistMap: EntityDeleteOrUpdateAdapter<SongPlaylistMap>

  private val __updateAdapterOfArtist: EntityDeleteOrUpdateAdapter<Artist>

  private val __updateAdapterOfAlbum: EntityDeleteOrUpdateAdapter<Album>

  private val __updateAdapterOfPlaylist: EntityDeleteOrUpdateAdapter<Playlist>

  private val __upsertAdapterOfLyrics: EntityUpsertAdapter<Lyrics>

  private val __upsertAdapterOfAlbum: EntityUpsertAdapter<Album>

  private val __upsertAdapterOfSongAlbumMap: EntityUpsertAdapter<SongAlbumMap>

  private val __upsertAdapterOfArtist: EntityUpsertAdapter<Artist>
  init {
    this.__db = __db
    this.__insertAdapterOfEvent = object : EntityInsertAdapter<Event>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `Event` (`id`,`songId`,`timestamp`,`playTime`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Event) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.songId)
        statement.bindLong(3, entity.timestamp)
        statement.bindLong(4, entity.playTime)
      }
    }
    this.__insertAdapterOfFormat = object : EntityInsertAdapter<Format>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `Format` (`songId`,`itag`,`mimeType`,`bitrate`,`contentLength`,`lastModified`,`loudnessDb`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Format) {
        statement.bindText(1, entity.songId)
        val _tmpItag: Int? = entity.itag
        if (_tmpItag == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpItag.toLong())
        }
        val _tmpMimeType: String? = entity.mimeType
        if (_tmpMimeType == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpMimeType)
        }
        val _tmpBitrate: Long? = entity.bitrate
        if (_tmpBitrate == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpBitrate)
        }
        val _tmpContentLength: Long? = entity.contentLength
        if (_tmpContentLength == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpContentLength)
        }
        val _tmpLastModified: Long? = entity.lastModified
        if (_tmpLastModified == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpLastModified)
        }
        val _tmpLoudnessDb: Float? = entity.loudnessDb
        if (_tmpLoudnessDb == null) {
          statement.bindNull(7)
        } else {
          statement.bindDouble(7, _tmpLoudnessDb.toDouble())
        }
      }
    }
    this.__insertAdapterOfSearchQuery = object : EntityInsertAdapter<SearchQuery>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `SearchQuery` (`id`,`query`) VALUES (nullif(?, 0),?)"

      protected override fun bind(statement: SQLiteStatement, entity: SearchQuery) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.query)
      }
    }
    this.__insertAdapterOfPlaylist = object : EntityInsertAdapter<Playlist>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `Playlist` (`id`,`name`,`browseId`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Playlist) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpBrowseId: String? = entity.browseId
        if (_tmpBrowseId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpBrowseId)
        }
      }
    }
    this.__insertAdapterOfSongPlaylistMap = object : EntityInsertAdapter<SongPlaylistMap>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `SongPlaylistMap` (`songId`,`playlistId`,`position`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SongPlaylistMap) {
        statement.bindText(1, entity.songId)
        statement.bindLong(2, entity.playlistId)
        statement.bindLong(3, entity.position.toLong())
      }
    }
    this.__insertAdapterOfSongArtistMap = object : EntityInsertAdapter<SongArtistMap>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `SongArtistMap` (`songId`,`artistId`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SongArtistMap) {
        statement.bindText(1, entity.songId)
        statement.bindText(2, entity.artistId)
      }
    }
    this.__insertAdapterOfSong = object : EntityInsertAdapter<Song>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `Song` (`id`,`title`,`artistsText`,`durationText`,`thumbnailUrl`,`likedAt`,`totalPlayTimeMs`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Song) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        val _tmpArtistsText: String? = entity.artistsText
        if (_tmpArtistsText == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpArtistsText)
        }
        val _tmpDurationText: String? = entity.durationText
        if (_tmpDurationText == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDurationText)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpThumbnailUrl)
        }
        val _tmpLikedAt: Long? = entity.likedAt
        if (_tmpLikedAt == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpLikedAt)
        }
        statement.bindLong(7, entity.totalPlayTimeMs)
      }
    }
    this.__insertAdapterOfQueuedMediaItem = object : EntityInsertAdapter<QueuedMediaItem>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `QueuedMediaItem` (`id`,`mediaItem`,`position`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: QueuedMediaItem) {
        statement.bindLong(1, entity.id)
        val _tmp: ByteArray? = Converters.mediaItemToByteArray(entity.mediaItem)
        if (_tmp == null) {
          statement.bindNull(2)
        } else {
          statement.bindBlob(2, _tmp)
        }
        val _tmpPosition: Long? = entity.position
        if (_tmpPosition == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpPosition)
        }
      }
    }
    this.__insertAdapterOfAlbum = object : EntityInsertAdapter<Album>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `Album` (`id`,`title`,`thumbnailUrl`,`year`,`authorsText`,`shareUrl`,`timestamp`,`bookmarkedAt`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Album) {
        statement.bindText(1, entity.id)
        val _tmpTitle: String? = entity.title
        if (_tmpTitle == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpTitle)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpYear: String? = entity.year
        if (_tmpYear == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpYear)
        }
        val _tmpAuthorsText: String? = entity.authorsText
        if (_tmpAuthorsText == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpAuthorsText)
        }
        val _tmpShareUrl: String? = entity.shareUrl
        if (_tmpShareUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpShareUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpBookmarkedAt)
        }
      }
    }
    this.__insertAdapterOfSongAlbumMap = object : EntityInsertAdapter<SongAlbumMap>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `SongAlbumMap` (`songId`,`albumId`,`position`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SongAlbumMap) {
        statement.bindText(1, entity.songId)
        statement.bindText(2, entity.albumId)
        val _tmpPosition: Int? = entity.position
        if (_tmpPosition == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpPosition.toLong())
        }
      }
    }
    this.__insertAdapterOfArtist = object : EntityInsertAdapter<Artist>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `Artist` (`id`,`name`,`thumbnailUrl`,`timestamp`,`bookmarkedAt`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Artist) {
        statement.bindText(1, entity.id)
        val _tmpName: String? = entity.name
        if (_tmpName == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpName)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpBookmarkedAt)
        }
      }
    }
    this.__insertAdapterOfSongArtistMap_1 = object : EntityInsertAdapter<SongArtistMap>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `SongArtistMap` (`songId`,`artistId`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SongArtistMap) {
        statement.bindText(1, entity.songId)
        statement.bindText(2, entity.artistId)
      }
    }
    this.__deleteAdapterOfSearchQuery = object : EntityDeleteOrUpdateAdapter<SearchQuery>() {
      protected override fun createQuery(): String = "DELETE FROM `SearchQuery` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SearchQuery) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__deleteAdapterOfPlaylist = object : EntityDeleteOrUpdateAdapter<Playlist>() {
      protected override fun createQuery(): String = "DELETE FROM `Playlist` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Playlist) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__deleteAdapterOfSongPlaylistMap = object : EntityDeleteOrUpdateAdapter<SongPlaylistMap>() {
      protected override fun createQuery(): String = "DELETE FROM `SongPlaylistMap` WHERE `songId` = ? AND `playlistId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SongPlaylistMap) {
        statement.bindText(1, entity.songId)
        statement.bindLong(2, entity.playlistId)
      }
    }
    this.__updateAdapterOfArtist = object : EntityDeleteOrUpdateAdapter<Artist>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `Artist` SET `id` = ?,`name` = ?,`thumbnailUrl` = ?,`timestamp` = ?,`bookmarkedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Artist) {
        statement.bindText(1, entity.id)
        val _tmpName: String? = entity.name
        if (_tmpName == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpName)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpBookmarkedAt)
        }
        statement.bindText(6, entity.id)
      }
    }
    this.__updateAdapterOfAlbum = object : EntityDeleteOrUpdateAdapter<Album>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `Album` SET `id` = ?,`title` = ?,`thumbnailUrl` = ?,`year` = ?,`authorsText` = ?,`shareUrl` = ?,`timestamp` = ?,`bookmarkedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Album) {
        statement.bindText(1, entity.id)
        val _tmpTitle: String? = entity.title
        if (_tmpTitle == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpTitle)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpYear: String? = entity.year
        if (_tmpYear == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpYear)
        }
        val _tmpAuthorsText: String? = entity.authorsText
        if (_tmpAuthorsText == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpAuthorsText)
        }
        val _tmpShareUrl: String? = entity.shareUrl
        if (_tmpShareUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpShareUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpBookmarkedAt)
        }
        statement.bindText(9, entity.id)
      }
    }
    this.__updateAdapterOfPlaylist = object : EntityDeleteOrUpdateAdapter<Playlist>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `Playlist` SET `id` = ?,`name` = ?,`browseId` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Playlist) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpBrowseId: String? = entity.browseId
        if (_tmpBrowseId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpBrowseId)
        }
        statement.bindLong(4, entity.id)
      }
    }
    this.__upsertAdapterOfLyrics = EntityUpsertAdapter<Lyrics>(object : EntityInsertAdapter<Lyrics>() {
      protected override fun createQuery(): String = "INSERT INTO `Lyrics` (`songId`,`fixed`,`synced`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Lyrics) {
        statement.bindText(1, entity.songId)
        val _tmpFixed: String? = entity.fixed
        if (_tmpFixed == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpFixed)
        }
        val _tmpSynced: String? = entity.synced
        if (_tmpSynced == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpSynced)
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<Lyrics>() {
      protected override fun createQuery(): String = "UPDATE `Lyrics` SET `songId` = ?,`fixed` = ?,`synced` = ? WHERE `songId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Lyrics) {
        statement.bindText(1, entity.songId)
        val _tmpFixed: String? = entity.fixed
        if (_tmpFixed == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpFixed)
        }
        val _tmpSynced: String? = entity.synced
        if (_tmpSynced == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpSynced)
        }
        statement.bindText(4, entity.songId)
      }
    })
    this.__upsertAdapterOfAlbum = EntityUpsertAdapter<Album>(object : EntityInsertAdapter<Album>() {
      protected override fun createQuery(): String = "INSERT INTO `Album` (`id`,`title`,`thumbnailUrl`,`year`,`authorsText`,`shareUrl`,`timestamp`,`bookmarkedAt`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Album) {
        statement.bindText(1, entity.id)
        val _tmpTitle: String? = entity.title
        if (_tmpTitle == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpTitle)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpYear: String? = entity.year
        if (_tmpYear == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpYear)
        }
        val _tmpAuthorsText: String? = entity.authorsText
        if (_tmpAuthorsText == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpAuthorsText)
        }
        val _tmpShareUrl: String? = entity.shareUrl
        if (_tmpShareUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpShareUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpBookmarkedAt)
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<Album>() {
      protected override fun createQuery(): String = "UPDATE `Album` SET `id` = ?,`title` = ?,`thumbnailUrl` = ?,`year` = ?,`authorsText` = ?,`shareUrl` = ?,`timestamp` = ?,`bookmarkedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Album) {
        statement.bindText(1, entity.id)
        val _tmpTitle: String? = entity.title
        if (_tmpTitle == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpTitle)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpYear: String? = entity.year
        if (_tmpYear == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpYear)
        }
        val _tmpAuthorsText: String? = entity.authorsText
        if (_tmpAuthorsText == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpAuthorsText)
        }
        val _tmpShareUrl: String? = entity.shareUrl
        if (_tmpShareUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpShareUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpBookmarkedAt)
        }
        statement.bindText(9, entity.id)
      }
    })
    this.__upsertAdapterOfSongAlbumMap = EntityUpsertAdapter<SongAlbumMap>(object : EntityInsertAdapter<SongAlbumMap>() {
      protected override fun createQuery(): String = "INSERT INTO `SongAlbumMap` (`songId`,`albumId`,`position`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SongAlbumMap) {
        statement.bindText(1, entity.songId)
        statement.bindText(2, entity.albumId)
        val _tmpPosition: Int? = entity.position
        if (_tmpPosition == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpPosition.toLong())
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<SongAlbumMap>() {
      protected override fun createQuery(): String = "UPDATE `SongAlbumMap` SET `songId` = ?,`albumId` = ?,`position` = ? WHERE `songId` = ? AND `albumId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SongAlbumMap) {
        statement.bindText(1, entity.songId)
        statement.bindText(2, entity.albumId)
        val _tmpPosition: Int? = entity.position
        if (_tmpPosition == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpPosition.toLong())
        }
        statement.bindText(4, entity.songId)
        statement.bindText(5, entity.albumId)
      }
    })
    this.__upsertAdapterOfArtist = EntityUpsertAdapter<Artist>(object : EntityInsertAdapter<Artist>() {
      protected override fun createQuery(): String = "INSERT INTO `Artist` (`id`,`name`,`thumbnailUrl`,`timestamp`,`bookmarkedAt`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Artist) {
        statement.bindText(1, entity.id)
        val _tmpName: String? = entity.name
        if (_tmpName == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpName)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpBookmarkedAt)
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<Artist>() {
      protected override fun createQuery(): String = "UPDATE `Artist` SET `id` = ?,`name` = ?,`thumbnailUrl` = ?,`timestamp` = ?,`bookmarkedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Artist) {
        statement.bindText(1, entity.id)
        val _tmpName: String? = entity.name
        if (_tmpName == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpName)
        }
        val _tmpThumbnailUrl: String? = entity.thumbnailUrl
        if (_tmpThumbnailUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpThumbnailUrl)
        }
        val _tmpTimestamp: Long? = entity.timestamp
        if (_tmpTimestamp == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpTimestamp)
        }
        val _tmpBookmarkedAt: Long? = entity.bookmarkedAt
        if (_tmpBookmarkedAt == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpBookmarkedAt)
        }
        statement.bindText(6, entity.id)
      }
    })
  }

  public override fun insert(event: Event): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfEvent.insert(_connection, event)
  }

  public override fun insert(format: Format): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfFormat.insert(_connection, format)
  }

  public override fun insert(searchQuery: SearchQuery): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfSearchQuery.insert(_connection, searchQuery)
  }

  public override fun insert(playlist: Playlist): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPlaylist.insertAndReturnId(_connection, playlist)
    _result
  }

  public override fun insert(songPlaylistMap: SongPlaylistMap): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSongPlaylistMap.insertAndReturnId(_connection, songPlaylistMap)
    _result
  }

  public override fun insert(songArtistMap: SongArtistMap): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSongArtistMap.insertAndReturnId(_connection, songArtistMap)
    _result
  }

  public override fun insert(song: Song): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSong.insertAndReturnId(_connection, song)
    _result
  }

  public override fun insert(queuedMediaItems: List<QueuedMediaItem>): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfQueuedMediaItem.insert(_connection, queuedMediaItems)
  }

  public override fun insert(album: Album, songAlbumMap: SongAlbumMap): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfAlbum.insert(_connection, album)
    __insertAdapterOfSongAlbumMap.insert(_connection, songAlbumMap)
  }

  public override fun insert(artists: List<Artist>, songArtistMaps: List<SongArtistMap>): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfArtist.insert(_connection, artists)
    __insertAdapterOfSongArtistMap_1.insert(_connection, songArtistMaps)
  }

  public override fun insertSongPlaylistMaps(songPlaylistMaps: List<SongPlaylistMap>): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfSongPlaylistMap.insert(_connection, songPlaylistMaps)
  }

  public override fun delete(searchQuery: SearchQuery): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfSearchQuery.handle(_connection, searchQuery)
  }

  public override fun delete(playlist: Playlist): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfPlaylist.handle(_connection, playlist)
  }

  public override fun delete(songPlaylistMap: SongPlaylistMap): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfSongPlaylistMap.handle(_connection, songPlaylistMap)
  }

  public override fun update(artist: Artist): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfArtist.handle(_connection, artist)
  }

  public override fun update(album: Album): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfAlbum.handle(_connection, album)
  }

  public override fun update(playlist: Playlist): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfPlaylist.handle(_connection, playlist)
  }

  public override fun insert(mediaItem: MediaItem, block: Function1<Song, Song>): Unit = performBlocking(__db, false, true) { _ ->
    super@DatabaseDao_Impl.insert(mediaItem, block)
  }

  public override fun upsert(lyrics: Lyrics): Unit = performBlocking(__db, false, true) { _connection ->
    __upsertAdapterOfLyrics.upsert(_connection, lyrics)
  }

  public override fun upsert(album: Album, songAlbumMaps: List<SongAlbumMap>): Unit = performBlocking(__db, false, true) { _connection ->
    __upsertAdapterOfAlbum.upsert(_connection, album)
    __upsertAdapterOfSongAlbumMap.upsert(_connection, songAlbumMaps)
  }

  public override fun upsert(songAlbumMap: SongAlbumMap): Unit = performBlocking(__db, false, true) { _connection ->
    __upsertAdapterOfSongAlbumMap.upsert(_connection, songAlbumMap)
  }

  public override fun upsert(artist: Artist): Unit = performBlocking(__db, false, true) { _connection ->
    __upsertAdapterOfArtist.upsert(_connection, artist)
  }

  public override fun songsByRowIdAsc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY ROWID"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsByRowIdDesc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY ROWID DESC"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsByTitleAsc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY title COLLATE NOCASE"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsByTitleDesc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY title COLLATE NOCASE DESC"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsByPlayTimeAsc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY totalPlayTimeMs"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsByPlayTimeDesc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY totalPlayTimeMs DESC"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsByArtistsAsc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY artistsText COLLATE NOCASE, title COLLATE NOCASE"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsByArtistsDesc(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE totalPlayTimeMs > 0 ORDER BY artistsText COLLATE NOCASE DESC, title COLLATE NOCASE"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun favorites(): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE likedAt IS NOT NULL ORDER BY likedAt DESC"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun queue(): List<QueuedMediaItem> {
    val _sql: String = "SELECT * FROM QueuedMediaItem"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfMediaItem: Int = getColumnIndexOrThrow(_stmt, "mediaItem")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _result: MutableList<QueuedMediaItem> = mutableListOf()
        while (_stmt.step()) {
          val _item: QueuedMediaItem
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpMediaItem: MediaItem
          val _tmp: ByteArray?
          if (_stmt.isNull(_columnIndexOfMediaItem)) {
            _tmp = null
          } else {
            _tmp = _stmt.getBlob(_columnIndexOfMediaItem)
          }
          val _tmp_1: MediaItem? = Converters.mediaItemFromByteArray(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'androidx.media3.common.MediaItem', but it was NULL.")
          } else {
            _tmpMediaItem = _tmp_1
          }
          val _tmpPosition: Long?
          if (_stmt.isNull(_columnIndexOfPosition)) {
            _tmpPosition = null
          } else {
            _tmpPosition = _stmt.getLong(_columnIndexOfPosition)
          }
          _item = QueuedMediaItem(_tmpId,_tmpMediaItem,_tmpPosition)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun queries(query: String): Flow<List<SearchQuery>> {
    val _sql: String = "SELECT * FROM SearchQuery WHERE `query` LIKE ? ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("SearchQuery")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfQuery: Int = getColumnIndexOrThrow(_stmt, "query")
        val _result: MutableList<SearchQuery> = mutableListOf()
        while (_stmt.step()) {
          val _item: SearchQuery
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpQuery: String
          _tmpQuery = _stmt.getText(_columnIndexOfQuery)
          _item = SearchQuery(_tmpId,_tmpQuery)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun queriesCount(): Flow<Int> {
    val _sql: String = "SELECT COUNT (*) FROM SearchQuery"
    return createFlow(__db, false, arrayOf("SearchQuery")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun song(id: String): Flow<Song?> {
    val _sql: String = "SELECT * FROM Song WHERE id = ?"
    return createFlow(__db, false, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: Song?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _result = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun likedAt(songId: String): Flow<Long?> {
    val _sql: String = "SELECT likedAt FROM Song WHERE id = ?"
    return createFlow(__db, false, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, songId)
        val _result: Long?
        if (_stmt.step()) {
          if (_stmt.isNull(0)) {
            _result = null
          } else {
            _result = _stmt.getLong(0)
          }
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun lyrics(songId: String): Flow<Lyrics?> {
    val _sql: String = "SELECT * FROM Lyrics WHERE songId = ?"
    return createFlow(__db, false, arrayOf("Lyrics")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, songId)
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "songId")
        val _columnIndexOfFixed: Int = getColumnIndexOrThrow(_stmt, "fixed")
        val _columnIndexOfSynced: Int = getColumnIndexOrThrow(_stmt, "synced")
        val _result: Lyrics?
        if (_stmt.step()) {
          val _tmpSongId: String
          _tmpSongId = _stmt.getText(_columnIndexOfSongId)
          val _tmpFixed: String?
          if (_stmt.isNull(_columnIndexOfFixed)) {
            _tmpFixed = null
          } else {
            _tmpFixed = _stmt.getText(_columnIndexOfFixed)
          }
          val _tmpSynced: String?
          if (_stmt.isNull(_columnIndexOfSynced)) {
            _tmpSynced = null
          } else {
            _tmpSynced = _stmt.getText(_columnIndexOfSynced)
          }
          _result = Lyrics(_tmpSongId,_tmpFixed,_tmpSynced)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun artist(id: String): Flow<Artist?> {
    val _sql: String = "SELECT * FROM Artist WHERE id = ?"
    return createFlow(__db, false, arrayOf("Artist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: Artist?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String?
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _result = Artist(_tmpId,_tmpName,_tmpThumbnailUrl,_tmpTimestamp,_tmpBookmarkedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun artistsByNameAsc(): Flow<List<Artist>> {
    val _sql: String = "SELECT * FROM Artist WHERE bookmarkedAt IS NOT NULL ORDER BY name COLLATE NOCASE"
    return createFlow(__db, false, arrayOf("Artist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Artist> = mutableListOf()
        while (_stmt.step()) {
          val _item: Artist
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String?
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Artist(_tmpId,_tmpName,_tmpThumbnailUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun artistsByNameDesc(): Flow<List<Artist>> {
    val _sql: String = "SELECT * FROM Artist WHERE bookmarkedAt IS NOT NULL ORDER BY name COLLATE NOCASE DESC"
    return createFlow(__db, false, arrayOf("Artist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Artist> = mutableListOf()
        while (_stmt.step()) {
          val _item: Artist
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String?
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Artist(_tmpId,_tmpName,_tmpThumbnailUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun artistsByRowIdAsc(): Flow<List<Artist>> {
    val _sql: String = "SELECT * FROM Artist WHERE bookmarkedAt IS NOT NULL ORDER BY bookmarkedAt"
    return createFlow(__db, false, arrayOf("Artist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Artist> = mutableListOf()
        while (_stmt.step()) {
          val _item: Artist
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String?
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Artist(_tmpId,_tmpName,_tmpThumbnailUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun artistsByRowIdDesc(): Flow<List<Artist>> {
    val _sql: String = "SELECT * FROM Artist WHERE bookmarkedAt IS NOT NULL ORDER BY bookmarkedAt DESC"
    return createFlow(__db, false, arrayOf("Artist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Artist> = mutableListOf()
        while (_stmt.step()) {
          val _item: Artist
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String?
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Artist(_tmpId,_tmpName,_tmpThumbnailUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun album(id: String): Flow<Album?> {
    val _sql: String = "SELECT * FROM Album WHERE id = ?"
    return createFlow(__db, false, arrayOf("Album")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfAuthorsText: Int = getColumnIndexOrThrow(_stmt, "authorsText")
        val _columnIndexOfShareUrl: Int = getColumnIndexOrThrow(_stmt, "shareUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: Album?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String?
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpAuthorsText: String?
          if (_stmt.isNull(_columnIndexOfAuthorsText)) {
            _tmpAuthorsText = null
          } else {
            _tmpAuthorsText = _stmt.getText(_columnIndexOfAuthorsText)
          }
          val _tmpShareUrl: String?
          if (_stmt.isNull(_columnIndexOfShareUrl)) {
            _tmpShareUrl = null
          } else {
            _tmpShareUrl = _stmt.getText(_columnIndexOfShareUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _result = Album(_tmpId,_tmpTitle,_tmpThumbnailUrl,_tmpYear,_tmpAuthorsText,_tmpShareUrl,_tmpTimestamp,_tmpBookmarkedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumTimestamp(id: String): Long? {
    val _sql: String = "SELECT timestamp FROM Album WHERE id = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _result: Long?
        if (_stmt.step()) {
          if (_stmt.isNull(0)) {
            _result = null
          } else {
            _result = _stmt.getLong(0)
          }
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumSongs(albumId: String): Flow<List<Song>> {
    val _sql: String = "SELECT `id`, `title`, `artistsText`, `durationText`, `thumbnailUrl`, `likedAt`, `totalPlayTimeMs` FROM (SELECT * FROM Song JOIN SongAlbumMap ON Song.id = SongAlbumMap.songId WHERE SongAlbumMap.albumId = ? AND position IS NOT NULL ORDER BY position)"
    return createFlow(__db, true, arrayOf("Song", "SongAlbumMap")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, albumId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfTitle: Int = 1
        val _columnIndexOfArtistsText: Int = 2
        val _columnIndexOfDurationText: Int = 3
        val _columnIndexOfThumbnailUrl: Int = 4
        val _columnIndexOfLikedAt: Int = 5
        val _columnIndexOfTotalPlayTimeMs: Int = 6
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumsByTitleAsc(): Flow<List<Album>> {
    val _sql: String = "SELECT * FROM Album WHERE bookmarkedAt IS NOT NULL ORDER BY title COLLATE NOCASE"
    return createFlow(__db, false, arrayOf("Album")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfAuthorsText: Int = getColumnIndexOrThrow(_stmt, "authorsText")
        val _columnIndexOfShareUrl: Int = getColumnIndexOrThrow(_stmt, "shareUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Album> = mutableListOf()
        while (_stmt.step()) {
          val _item: Album
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String?
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpAuthorsText: String?
          if (_stmt.isNull(_columnIndexOfAuthorsText)) {
            _tmpAuthorsText = null
          } else {
            _tmpAuthorsText = _stmt.getText(_columnIndexOfAuthorsText)
          }
          val _tmpShareUrl: String?
          if (_stmt.isNull(_columnIndexOfShareUrl)) {
            _tmpShareUrl = null
          } else {
            _tmpShareUrl = _stmt.getText(_columnIndexOfShareUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Album(_tmpId,_tmpTitle,_tmpThumbnailUrl,_tmpYear,_tmpAuthorsText,_tmpShareUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumsByTitleDesc(): Flow<List<Album>> {
    val _sql: String = "SELECT * FROM Album WHERE bookmarkedAt IS NOT NULL ORDER BY title COLLATE NOCASE DESC"
    return createFlow(__db, false, arrayOf("Album")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfAuthorsText: Int = getColumnIndexOrThrow(_stmt, "authorsText")
        val _columnIndexOfShareUrl: Int = getColumnIndexOrThrow(_stmt, "shareUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Album> = mutableListOf()
        while (_stmt.step()) {
          val _item: Album
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String?
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpAuthorsText: String?
          if (_stmt.isNull(_columnIndexOfAuthorsText)) {
            _tmpAuthorsText = null
          } else {
            _tmpAuthorsText = _stmt.getText(_columnIndexOfAuthorsText)
          }
          val _tmpShareUrl: String?
          if (_stmt.isNull(_columnIndexOfShareUrl)) {
            _tmpShareUrl = null
          } else {
            _tmpShareUrl = _stmt.getText(_columnIndexOfShareUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Album(_tmpId,_tmpTitle,_tmpThumbnailUrl,_tmpYear,_tmpAuthorsText,_tmpShareUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumsByYearAsc(): Flow<List<Album>> {
    val _sql: String = "SELECT * FROM Album WHERE bookmarkedAt IS NOT NULL ORDER BY year"
    return createFlow(__db, false, arrayOf("Album")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfAuthorsText: Int = getColumnIndexOrThrow(_stmt, "authorsText")
        val _columnIndexOfShareUrl: Int = getColumnIndexOrThrow(_stmt, "shareUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Album> = mutableListOf()
        while (_stmt.step()) {
          val _item: Album
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String?
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpAuthorsText: String?
          if (_stmt.isNull(_columnIndexOfAuthorsText)) {
            _tmpAuthorsText = null
          } else {
            _tmpAuthorsText = _stmt.getText(_columnIndexOfAuthorsText)
          }
          val _tmpShareUrl: String?
          if (_stmt.isNull(_columnIndexOfShareUrl)) {
            _tmpShareUrl = null
          } else {
            _tmpShareUrl = _stmt.getText(_columnIndexOfShareUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Album(_tmpId,_tmpTitle,_tmpThumbnailUrl,_tmpYear,_tmpAuthorsText,_tmpShareUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumsByYearDesc(): Flow<List<Album>> {
    val _sql: String = "SELECT * FROM Album WHERE bookmarkedAt IS NOT NULL ORDER BY year DESC"
    return createFlow(__db, false, arrayOf("Album")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfAuthorsText: Int = getColumnIndexOrThrow(_stmt, "authorsText")
        val _columnIndexOfShareUrl: Int = getColumnIndexOrThrow(_stmt, "shareUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Album> = mutableListOf()
        while (_stmt.step()) {
          val _item: Album
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String?
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpAuthorsText: String?
          if (_stmt.isNull(_columnIndexOfAuthorsText)) {
            _tmpAuthorsText = null
          } else {
            _tmpAuthorsText = _stmt.getText(_columnIndexOfAuthorsText)
          }
          val _tmpShareUrl: String?
          if (_stmt.isNull(_columnIndexOfShareUrl)) {
            _tmpShareUrl = null
          } else {
            _tmpShareUrl = _stmt.getText(_columnIndexOfShareUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Album(_tmpId,_tmpTitle,_tmpThumbnailUrl,_tmpYear,_tmpAuthorsText,_tmpShareUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumsByRowIdAsc(): Flow<List<Album>> {
    val _sql: String = "SELECT * FROM Album WHERE bookmarkedAt IS NOT NULL ORDER BY bookmarkedAt"
    return createFlow(__db, false, arrayOf("Album")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfAuthorsText: Int = getColumnIndexOrThrow(_stmt, "authorsText")
        val _columnIndexOfShareUrl: Int = getColumnIndexOrThrow(_stmt, "shareUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Album> = mutableListOf()
        while (_stmt.step()) {
          val _item: Album
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String?
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpAuthorsText: String?
          if (_stmt.isNull(_columnIndexOfAuthorsText)) {
            _tmpAuthorsText = null
          } else {
            _tmpAuthorsText = _stmt.getText(_columnIndexOfAuthorsText)
          }
          val _tmpShareUrl: String?
          if (_stmt.isNull(_columnIndexOfShareUrl)) {
            _tmpShareUrl = null
          } else {
            _tmpShareUrl = _stmt.getText(_columnIndexOfShareUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Album(_tmpId,_tmpTitle,_tmpThumbnailUrl,_tmpYear,_tmpAuthorsText,_tmpShareUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun albumsByRowIdDesc(): Flow<List<Album>> {
    val _sql: String = "SELECT * FROM Album WHERE bookmarkedAt IS NOT NULL ORDER BY bookmarkedAt DESC"
    return createFlow(__db, false, arrayOf("Album")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfAuthorsText: Int = getColumnIndexOrThrow(_stmt, "authorsText")
        val _columnIndexOfShareUrl: Int = getColumnIndexOrThrow(_stmt, "shareUrl")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfBookmarkedAt: Int = getColumnIndexOrThrow(_stmt, "bookmarkedAt")
        val _result: MutableList<Album> = mutableListOf()
        while (_stmt.step()) {
          val _item: Album
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String?
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpAuthorsText: String?
          if (_stmt.isNull(_columnIndexOfAuthorsText)) {
            _tmpAuthorsText = null
          } else {
            _tmpAuthorsText = _stmt.getText(_columnIndexOfAuthorsText)
          }
          val _tmpShareUrl: String?
          if (_stmt.isNull(_columnIndexOfShareUrl)) {
            _tmpShareUrl = null
          } else {
            _tmpShareUrl = _stmt.getText(_columnIndexOfShareUrl)
          }
          val _tmpTimestamp: Long?
          if (_stmt.isNull(_columnIndexOfTimestamp)) {
            _tmpTimestamp = null
          } else {
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          }
          val _tmpBookmarkedAt: Long?
          if (_stmt.isNull(_columnIndexOfBookmarkedAt)) {
            _tmpBookmarkedAt = null
          } else {
            _tmpBookmarkedAt = _stmt.getLong(_columnIndexOfBookmarkedAt)
          }
          _item = Album(_tmpId,_tmpTitle,_tmpThumbnailUrl,_tmpYear,_tmpAuthorsText,_tmpShareUrl,_tmpTimestamp,_tmpBookmarkedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlist(id: Long): Flow<Playlist?> {
    val _sql: String = "SELECT * FROM Playlist WHERE id = ?"
    return createFlow(__db, false, arrayOf("Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfBrowseId: Int = getColumnIndexOrThrow(_stmt, "browseId")
        val _result: Playlist?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpBrowseId: String?
          if (_stmt.isNull(_columnIndexOfBrowseId)) {
            _tmpBrowseId = null
          } else {
            _tmpBrowseId = _stmt.getText(_columnIndexOfBrowseId)
          }
          _result = Playlist(_tmpId,_tmpName,_tmpBrowseId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistSongs(id: Long): Flow<List<Song>> {
    val _sql: String = "SELECT Song.* FROM SongPlaylistMap JOIN Song on Song.id = SongPlaylistMap.songId WHERE playlistId = ? ORDER BY position"
    return createFlow(__db, true, arrayOf("SongPlaylistMap", "Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistWithSongs(id: Long): Flow<PlaylistWithSongs?> {
    val _sql: String = "SELECT * FROM Playlist WHERE id = ?"
    return createFlow(__db, true, arrayOf("SortedSongPlaylistMap", "Song", "Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfBrowseId: Int = getColumnIndexOrThrow(_stmt, "browseId")
        val _collectionSongs: LongSparseArray<MutableList<Song>> = LongSparseArray<MutableList<Song>>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfId)
          if (!_collectionSongs.containsKey(_tmpKey)) {
            _collectionSongs.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipSongAscomGithubMusicyouModelsSong(_connection, _collectionSongs)
        val _result: PlaylistWithSongs?
        if (_stmt.step()) {
          val _tmpPlaylist: Playlist
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpBrowseId: String?
          if (_stmt.isNull(_columnIndexOfBrowseId)) {
            _tmpBrowseId = null
          } else {
            _tmpBrowseId = _stmt.getText(_columnIndexOfBrowseId)
          }
          _tmpPlaylist = Playlist(_tmpId,_tmpName,_tmpBrowseId)
          val _tmpSongsCollection: MutableList<Song>
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfId)
          _tmpSongsCollection = checkNotNull(_collectionSongs.get(_tmpKey_1))
          _result = PlaylistWithSongs(_tmpPlaylist,_tmpSongsCollection)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistPreviewsByNameAsc(): Flow<List<PlaylistPreview>> {
    val _sql: String = "SELECT id, name, (SELECT COUNT(*) FROM SongPlaylistMap WHERE playlistId = id) as songCount FROM Playlist ORDER BY name COLLATE NOCASE"
    return createFlow(__db, true, arrayOf("SongPlaylistMap", "Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _columnIndexOfSongCount: Int = 2
        val _result: MutableList<PlaylistPreview> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistPreview
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = PlaylistPreview(_tmpId,_tmpName,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistPreviewsByNameDesc(): Flow<List<PlaylistPreview>> {
    val _sql: String = "SELECT id, name, (SELECT COUNT(*) FROM SongPlaylistMap WHERE playlistId = id) as songCount FROM Playlist ORDER BY name COLLATE NOCASE DESC"
    return createFlow(__db, true, arrayOf("SongPlaylistMap", "Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _columnIndexOfSongCount: Int = 2
        val _result: MutableList<PlaylistPreview> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistPreview
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = PlaylistPreview(_tmpId,_tmpName,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistPreviewsByDateAddedAsc(): Flow<List<PlaylistPreview>> {
    val _sql: String = "SELECT id, name, (SELECT COUNT(*) FROM SongPlaylistMap WHERE playlistId = id) as songCount FROM Playlist ORDER BY ROWID"
    return createFlow(__db, true, arrayOf("SongPlaylistMap", "Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _columnIndexOfSongCount: Int = 2
        val _result: MutableList<PlaylistPreview> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistPreview
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = PlaylistPreview(_tmpId,_tmpName,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistPreviewsByDateAddedDesc(): Flow<List<PlaylistPreview>> {
    val _sql: String = "SELECT id, name, (SELECT COUNT(*) FROM SongPlaylistMap WHERE playlistId = id) as songCount FROM Playlist ORDER BY ROWID DESC"
    return createFlow(__db, true, arrayOf("SongPlaylistMap", "Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _columnIndexOfSongCount: Int = 2
        val _result: MutableList<PlaylistPreview> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistPreview
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = PlaylistPreview(_tmpId,_tmpName,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistPreviewsByDateSongCountAsc(): Flow<List<PlaylistPreview>> {
    val _sql: String = "SELECT id, name, (SELECT COUNT(*) FROM SongPlaylistMap WHERE playlistId = id) as songCount FROM Playlist ORDER BY songCount"
    return createFlow(__db, true, arrayOf("SongPlaylistMap", "Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _columnIndexOfSongCount: Int = 2
        val _result: MutableList<PlaylistPreview> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistPreview
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = PlaylistPreview(_tmpId,_tmpName,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistPreviewsByDateSongCountDesc(): Flow<List<PlaylistPreview>> {
    val _sql: String = "SELECT id, name, (SELECT COUNT(*) FROM SongPlaylistMap WHERE playlistId = id) as songCount FROM Playlist ORDER BY songCount DESC"
    return createFlow(__db, true, arrayOf("SongPlaylistMap", "Playlist")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _columnIndexOfSongCount: Int = 2
        val _result: MutableList<PlaylistPreview> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistPreview
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = PlaylistPreview(_tmpId,_tmpName,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun playlistThumbnailUrls(id: Long): Flow<List<String>> {
    val _sql: String = "SELECT thumbnailUrl FROM Song JOIN SongPlaylistMap ON id = songId WHERE playlistId = ? ORDER BY position LIMIT 4"
    return createFlow(__db, false, arrayOf("Song", "SongPlaylistMap")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun artistSongs(artistId: String): Flow<List<Song>> {
    val _sql: String = "SELECT `id`, `title`, `artistsText`, `durationText`, `thumbnailUrl`, `likedAt`, `totalPlayTimeMs` FROM (SELECT * FROM Song JOIN SongArtistMap ON Song.id = SongArtistMap.songId WHERE SongArtistMap.artistId = ? AND totalPlayTimeMs > 0 ORDER BY Song.ROWID DESC)"
    return createFlow(__db, true, arrayOf("Song", "SongArtistMap")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, artistId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfTitle: Int = 1
        val _columnIndexOfArtistsText: Int = 2
        val _columnIndexOfDurationText: Int = 3
        val _columnIndexOfThumbnailUrl: Int = 4
        val _columnIndexOfLikedAt: Int = 5
        val _columnIndexOfTotalPlayTimeMs: Int = 6
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun format(songId: String): Flow<Format?> {
    val _sql: String = "SELECT * FROM Format WHERE songId = ?"
    return createFlow(__db, false, arrayOf("Format")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, songId)
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "songId")
        val _columnIndexOfItag: Int = getColumnIndexOrThrow(_stmt, "itag")
        val _columnIndexOfMimeType: Int = getColumnIndexOrThrow(_stmt, "mimeType")
        val _columnIndexOfBitrate: Int = getColumnIndexOrThrow(_stmt, "bitrate")
        val _columnIndexOfContentLength: Int = getColumnIndexOrThrow(_stmt, "contentLength")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfLoudnessDb: Int = getColumnIndexOrThrow(_stmt, "loudnessDb")
        val _result: Format?
        if (_stmt.step()) {
          val _tmpSongId: String
          _tmpSongId = _stmt.getText(_columnIndexOfSongId)
          val _tmpItag: Int?
          if (_stmt.isNull(_columnIndexOfItag)) {
            _tmpItag = null
          } else {
            _tmpItag = _stmt.getLong(_columnIndexOfItag).toInt()
          }
          val _tmpMimeType: String?
          if (_stmt.isNull(_columnIndexOfMimeType)) {
            _tmpMimeType = null
          } else {
            _tmpMimeType = _stmt.getText(_columnIndexOfMimeType)
          }
          val _tmpBitrate: Long?
          if (_stmt.isNull(_columnIndexOfBitrate)) {
            _tmpBitrate = null
          } else {
            _tmpBitrate = _stmt.getLong(_columnIndexOfBitrate)
          }
          val _tmpContentLength: Long?
          if (_stmt.isNull(_columnIndexOfContentLength)) {
            _tmpContentLength = null
          } else {
            _tmpContentLength = _stmt.getLong(_columnIndexOfContentLength)
          }
          val _tmpLastModified: Long?
          if (_stmt.isNull(_columnIndexOfLastModified)) {
            _tmpLastModified = null
          } else {
            _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          }
          val _tmpLoudnessDb: Float?
          if (_stmt.isNull(_columnIndexOfLoudnessDb)) {
            _tmpLoudnessDb = null
          } else {
            _tmpLoudnessDb = _stmt.getDouble(_columnIndexOfLoudnessDb).toFloat()
          }
          _result = Format(_tmpSongId,_tmpItag,_tmpMimeType,_tmpBitrate,_tmpContentLength,_tmpLastModified,_tmpLoudnessDb)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songsWithContentLength(): Flow<List<SongWithContentLength>> {
    val _sql: String = "SELECT Song.*, contentLength FROM Song JOIN Format ON id = songId WHERE contentLength IS NOT NULL AND totalPlayTimeMs > 0 ORDER BY Song.ROWID DESC"
    return createFlow(__db, true, arrayOf("Song", "Format")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _columnIndexOfContentLength: Int = getColumnIndexOrThrow(_stmt, "contentLength")
        val _result: MutableList<SongWithContentLength> = mutableListOf()
        while (_stmt.step()) {
          val _item: SongWithContentLength
          val _tmpContentLength: Long?
          if (_stmt.isNull(_columnIndexOfContentLength)) {
            _tmpContentLength = null
          } else {
            _tmpContentLength = _stmt.getLong(_columnIndexOfContentLength)
          }
          val _tmpSong: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _tmpSong = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _item = SongWithContentLength(_tmpSong,_tmpContentLength)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun loudnessDb(songId: String): Flow<Float?> {
    val _sql: String = "SELECT loudnessDb FROM Format WHERE songId = ?"
    return createFlow(__db, false, arrayOf("Format")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, songId)
        val _result: Float?
        if (_stmt.step()) {
          if (_stmt.isNull(0)) {
            _result = null
          } else {
            _result = _stmt.getDouble(0).toFloat()
          }
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun search(query: String): Flow<List<Song>> {
    val _sql: String = "SELECT * FROM Song WHERE title LIKE ? OR artistsText LIKE ?"
    return createFlow(__db, false, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: MutableList<Song> = mutableListOf()
        while (_stmt.step()) {
          val _item: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songAlbumInfo(songId: String): Info? {
    val _sql: String = "SELECT albumId AS id, NULL AS name FROM SongAlbumMap WHERE songId = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, songId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _result: Info?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String?
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName)
          }
          _result = Info(_tmpId,_tmpName)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun songArtistInfo(songId: String): List<Info> {
    val _sql: String = "SELECT id, name FROM Artist LEFT JOIN SongArtistMap ON id = artistId WHERE songId = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, songId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfName: Int = 1
        val _result: MutableList<Info> = mutableListOf()
        while (_stmt.step()) {
          val _item: Info
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String?
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName)
          }
          _item = Info(_tmpId,_tmpName)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun trending(now: Long): Flow<Song?> {
    val _sql: String = "SELECT Song.* FROM Event JOIN Song ON Song.id = songId GROUP BY songId ORDER BY SUM(CAST(playTime AS REAL) / (((? - timestamp) / 86400000) + 1)) DESC LIMIT 1"
    return createFlow(__db, true, arrayOf("Event", "Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, now)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: Song?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _result = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun lastPlayed(): Flow<Song?> {
    val _sql: String = "SELECT Song.* FROM Event JOIN Song ON Song.id = songId GROUP BY songId ORDER BY timestamp DESC LIMIT 1"
    return createFlow(__db, true, arrayOf("Event", "Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: Song?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _result = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun randomSong(): Flow<Song?> {
    val _sql: String = "SELECT * FROM Song ORDER BY RANDOM() LIMIT 1"
    return createFlow(__db, true, arrayOf("Song")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistsText: Int = getColumnIndexOrThrow(_stmt, "artistsText")
        val _columnIndexOfDurationText: Int = getColumnIndexOrThrow(_stmt, "durationText")
        val _columnIndexOfThumbnailUrl: Int = getColumnIndexOrThrow(_stmt, "thumbnailUrl")
        val _columnIndexOfLikedAt: Int = getColumnIndexOrThrow(_stmt, "likedAt")
        val _columnIndexOfTotalPlayTimeMs: Int = getColumnIndexOrThrow(_stmt, "totalPlayTimeMs")
        val _result: Song?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _result = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun eventsCount(): Flow<Int> {
    val _sql: String = "SELECT COUNT (*) FROM Event"
    return createFlow(__db, false, arrayOf("Event")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearQueue() {
    val _sql: String = "DELETE FROM QueuedMediaItem"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearQueries() {
    val _sql: String = "DELETE FROM SearchQuery"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun like(songId: String, likedAt: Long?): Int {
    val _sql: String = "UPDATE Song SET likedAt = ? WHERE id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (likedAt == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, likedAt)
        }
        _argIndex = 2
        _stmt.bindText(_argIndex, songId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateDurationText(songId: String, durationText: String): Int {
    val _sql: String = "UPDATE Song SET durationText = ? WHERE id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, durationText)
        _argIndex = 2
        _stmt.bindText(_argIndex, songId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun incrementTotalPlayTimeMs(id: String, addition: Long) {
    val _sql: String = "UPDATE Song SET totalPlayTimeMs = totalPlayTimeMs + ? WHERE id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, addition)
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun move(
    playlistId: Long,
    fromPosition: Int,
    toPosition: Int,
  ) {
    val _sql: String = """
        |
        |        UPDATE SongPlaylistMap SET position = 
        |          CASE 
        |            WHEN position < ? THEN position + 1
        |            WHEN position > ? THEN position - 1
        |            ELSE ?
        |          END 
        |        WHERE playlistId = ? AND position BETWEEN MIN(?,?) and MAX(?,?)
        |    
        """.trimMargin()
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fromPosition.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, fromPosition.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, toPosition.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, playlistId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, fromPosition.toLong())
        _argIndex = 6
        _stmt.bindLong(_argIndex, toPosition.toLong())
        _argIndex = 7
        _stmt.bindLong(_argIndex, fromPosition.toLong())
        _argIndex = 8
        _stmt.bindLong(_argIndex, toPosition.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearPlaylist(id: Long) {
    val _sql: String = "DELETE FROM SongPlaylistMap WHERE playlistId = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearAlbum(id: String) {
    val _sql: String = "DELETE FROM SongAlbumMap WHERE albumId = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearEvents() {
    val _sql: String = "DELETE FROM Event"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearEventsFor(songId: String) {
    val _sql: String = "DELETE FROM Event WHERE songId = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, songId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun raw(supportSQLiteQuery: SupportSQLiteQuery): Int {
    val _rawQuery: RoomRawQuery = RoomSQLiteQuery.copyFrom(supportSQLiteQuery).toRoomRawQuery()
    val _sql: String = _rawQuery.sql
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _rawQuery.getBindingFunction().invoke(_stmt)
        val _result: Int
        if (_stmt.step()) {
          _result = _stmt.getLong(0).toInt()
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __fetchRelationshipSongAscomGithubMusicyouModelsSong(_connection: SQLiteConnection, _map: LongSparseArray<MutableList<Song>>) {
    if (_map.isEmpty()) {
      return
    }
    if (_map.size() > 999) {
      recursiveFetchLongSparseArray(_map, true) { _tmpMap ->
        __fetchRelationshipSongAscomGithubMusicyouModelsSong(_connection, _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `Song`.`id` AS `id`,`Song`.`title` AS `title`,`Song`.`artistsText` AS `artistsText`,`Song`.`durationText` AS `durationText`,`Song`.`thumbnailUrl` AS `thumbnailUrl`,`Song`.`likedAt` AS `likedAt`,`Song`.`totalPlayTimeMs` AS `totalPlayTimeMs`,_junction.`playlistId` FROM `SortedSongPlaylistMap` AS _junction INNER JOIN `Song` ON (_junction.`songId` = `Song`.`id`) WHERE _junction.`playlistId` IN (")
    val _inputSize: Int = _map.size()
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (i in 0 until _map.size()) {
      val _item: Long = _map.keyAt(i)
      _stmt.bindLong(_argIndex, _item)
      _argIndex++
    }
    try {
      // _junction.playlistId
      val _itemKeyIndex: Int = 7
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfId: Int = 0
      val _columnIndexOfTitle: Int = 1
      val _columnIndexOfArtistsText: Int = 2
      val _columnIndexOfDurationText: Int = 3
      val _columnIndexOfThumbnailUrl: Int = 4
      val _columnIndexOfLikedAt: Int = 5
      val _columnIndexOfTotalPlayTimeMs: Int = 6
      while (_stmt.step()) {
        val _tmpKey: Long
        _tmpKey = _stmt.getLong(_itemKeyIndex)
        val _tmpRelation: MutableList<Song>? = _map.get(_tmpKey)
        if (_tmpRelation != null) {
          val _item_1: Song
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistsText: String?
          if (_stmt.isNull(_columnIndexOfArtistsText)) {
            _tmpArtistsText = null
          } else {
            _tmpArtistsText = _stmt.getText(_columnIndexOfArtistsText)
          }
          val _tmpDurationText: String?
          if (_stmt.isNull(_columnIndexOfDurationText)) {
            _tmpDurationText = null
          } else {
            _tmpDurationText = _stmt.getText(_columnIndexOfDurationText)
          }
          val _tmpThumbnailUrl: String?
          if (_stmt.isNull(_columnIndexOfThumbnailUrl)) {
            _tmpThumbnailUrl = null
          } else {
            _tmpThumbnailUrl = _stmt.getText(_columnIndexOfThumbnailUrl)
          }
          val _tmpLikedAt: Long?
          if (_stmt.isNull(_columnIndexOfLikedAt)) {
            _tmpLikedAt = null
          } else {
            _tmpLikedAt = _stmt.getLong(_columnIndexOfLikedAt)
          }
          val _tmpTotalPlayTimeMs: Long
          _tmpTotalPlayTimeMs = _stmt.getLong(_columnIndexOfTotalPlayTimeMs)
          _item_1 = Song(_tmpId,_tmpTitle,_tmpArtistsText,_tmpDurationText,_tmpThumbnailUrl,_tmpLikedAt,_tmpTotalPlayTimeMs)
          _tmpRelation.add(_item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
