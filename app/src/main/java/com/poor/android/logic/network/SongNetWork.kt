package com.poor.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SongNetWork {

    private val songService = ServiceCreator.create<ISongService>()

    // 通过搜索获得歌曲信息
    suspend fun searchSongs(keywords: String) = songService.searchSongs(keywords).await()
    suspend fun getSongsDetail(ids: String) = songService.getSongsDetail(ids).await()
    suspend fun getSongMp3Url(id: Int) = songService.getSongMp3Url(id).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null!"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}