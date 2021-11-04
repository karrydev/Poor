package com.poor.android.logic.repository

import androidx.lifecycle.liveData
import com.poor.android.logic.model.song.cloud.Song
import com.poor.android.logic.model.db.SongDatabase
import com.poor.android.logic.model.network.SongNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.StringBuilder
import kotlin.coroutines.CoroutineContext

object SongRepository {

    private val songDao by lazy { SongDatabase.getDatabase().songDao() }

    fun searchSongsSuggest(keywords: String) = fire(Dispatchers.IO) {
        val searchSuggest = SongNetWork.searchSongsSuggest(keywords)
        if (searchSuggest.code == 200) {
            val songs = searchSuggest.result
            Result.success(songs)
        } else {
            Result.failure(RuntimeException("searchSongsResponse code is ${searchSuggest.code}"))
        }
    }

    /************************************ 数据库操作 start *************************************/

    suspend fun insertSong(song: Song) = songDao.insertSong(song)

    suspend fun updateSong(song: Song) = songDao.updateSong(song)

    suspend fun querySongById(songId: Int) = songDao.querySongById(songId)

    suspend fun querySongByName(songName: String) = songDao.querySongByName(songName)

    suspend fun queryAllSongs() = songDao.queryAllSongs()

    suspend fun deleteSong(song: Song) = songDao.deleteSong(song)

    suspend fun deleteSongById(songId: Int) = songDao.deleteSongById(songId)

    suspend fun deleteSongByName(songName: String) = songDao.deleteSongByName(songName)

    suspend fun deleteAllSongs() = songDao.deleteAllSong()

    /************************************ 数据库操作 end *************************************/

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    /************************************ 网易云音乐 start *************************************/

    fun searchCloudSongs(keywords: String) = fire(Dispatchers.IO) {
        val cloudSongs = SongNetWork.searchCloudSongs(keywords)
        if (cloudSongs.code == 200) {
            val songs = cloudSongs.result
            Result.success(songs)
        } else {
            Result.failure(RuntimeException("searchSongsResponse code is ${cloudSongs.code}"))
        }
    }

    fun getCloudSongsDetail(vararg ids: Int) = fire(Dispatchers.IO) {
        val idsStr = StringBuilder().run {
            for (id in ids.indices) {
                append(ids[id])
                if (id <= ids.size - 1) {
                    append(",")
                }
            }
            toString()
        }
        val cloudSongsDetail = SongNetWork.getCloudSongsDetail(idsStr)
        if (cloudSongsDetail.code == 200) {
            val songs = cloudSongsDetail.songs
            Result.success(songs)
        } else {
            Result.failure(RuntimeException("songsDetailResponse code is ${cloudSongsDetail.code}"))
        }
    }

    fun getCloudSongMp3Url(id: Int) = fire(Dispatchers.IO) {
        val cloudSongMp3Url = SongNetWork.getCloudSongMp3Url(id)
        if (cloudSongMp3Url.code == 200) {
            val data = cloudSongMp3Url.data
            Result.success(data)
        } else {
            Result.failure(RuntimeException("songsDetailResponse code is ${cloudSongMp3Url.code}"))
        }
    }

    fun getCloudSongLyric(id: Int) = fire(Dispatchers.IO) {
        val cloudSongLyric = SongNetWork.getCloudSongLyric(id)
        if (cloudSongLyric.code == 200) {
            val lrc = cloudSongLyric.lrc
            Result.success(lrc)
        } else {
            Result.failure(RuntimeException("songsDetailResponse code is ${cloudSongLyric.code}"))
        }
    }

    /************************************ 网易云音乐 end *************************************/
}