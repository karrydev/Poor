package com.poor.android.logic.model.network.cloudmusic

import com.poor.android.logic.model.cloudmusic.CloudSongLyric
import com.poor.android.logic.model.cloudmusic.SearchCloudSongsResponse
import com.poor.android.logic.model.cloudmusic.CloudSongsDetailResponse
import com.poor.android.logic.model.cloudmusic.CloudSongResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ICloudService {

    /**
     * 搜索歌曲
     * keywords：搜索关键字
     */
    @GET("search?")
    fun searchCloudSongs(@Query("keywords") keywords: String): Call<SearchCloudSongsResponse>

    /**
     * 搜索歌曲详情
     * ids：歌曲id，可传入多个
     */
    @GET("song/detail?")
    fun getCloudSongsDetail(@Query("ids") ids: String): Call<CloudSongsDetailResponse>

    /**
     * 获取指定歌曲的Mp3Url
     * id：歌曲id
     */
    @GET("song/url?")
    fun getCloudSongMp3Url(@Query("id") id: Int): Call<CloudSongResponse>

    /**
     * 获取指定歌曲的歌词
     * id：歌曲id
     */
    @GET("lyric?")
    fun getCloudSongLyric(@Query("id") id: Int): Call<CloudSongLyric>
}