package com.poor.android.logic.model.network

import com.poor.android.logic.model.SongType
import com.poor.android.logic.model.network.cloud.ICloudService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SongNetWork {

    private val cloudService = ServiceCreator.create<ICloudService>(SongType.CloudMusic)

    suspend fun searchSongsSuggest(keywords: String) = cloudService.searchSongsSuggest(keywords).await()
    /**
     * 网易云音乐
     */
    suspend fun searchCloudSongs(keywords: String) = cloudService.searchCloudSongs(keywords).await()
    suspend fun getCloudSongsDetail(ids: String) = cloudService.getCloudSongsDetail(ids).await()
    suspend fun getCloudSongMp3Url(id: Int) = cloudService.getCloudSongMp3Url(id).await()
    suspend fun getCloudSongLyric(id: Int) = cloudService.getCloudSongLyric(id).await()

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