package com.poor.android.logic.repository

import androidx.lifecycle.liveData
import com.poor.android.logic.network.SongNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.StringBuilder
import kotlin.coroutines.CoroutineContext

object SongRepository {

    fun searchSongs(keywords: String) = fire(Dispatchers.IO) {
        val searchSongsResponse = SongNetWork.searchSongs(keywords)
        if (searchSongsResponse.code == 200) {
            val songs = searchSongsResponse.result
            Result.success(songs)
        } else {
            Result.failure(RuntimeException("searchSongsResponse code is ${searchSongsResponse.code}"))
        }
    }

    fun getSongsDetail(vararg ids: Int) = fire(Dispatchers.IO) {
        val idsStr = StringBuilder().run {
            for (id in ids.indices) {
                append(ids[id])
                if (id <= ids.size - 1) {
                    append(",")
                }
            }
            toString()
        }
        val songsDetailResponse = SongNetWork.getSongsDetail(idsStr)
        if (songsDetailResponse.code == 200) {
            val songs = songsDetailResponse.songs
            Result.success(songs)
        } else {
            Result.failure(RuntimeException("songsDetailResponse code is ${songsDetailResponse.code}"))
        }
    }

    fun getSongMp3Url(id: Int) = fire(Dispatchers.IO) {
        val songMp3Url = SongNetWork.getSongMp3Url(id)
        if (songMp3Url.code == 200) {
            val data = songMp3Url.data
            Result.success(data)
        } else {
            Result.failure(RuntimeException("songsDetailResponse code is ${songMp3Url.code}"))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}