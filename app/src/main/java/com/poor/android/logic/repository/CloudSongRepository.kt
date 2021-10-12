package com.poor.android.logic.repository

import androidx.lifecycle.liveData
import com.poor.android.logic.network.SongNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.StringBuilder
import kotlin.coroutines.CoroutineContext

object CloudSongRepository {

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

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
}