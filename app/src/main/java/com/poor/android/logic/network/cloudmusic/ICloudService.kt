package com.poor.android.logic.network

import com.poor.android.logic.model.SearchSongResponse
import com.poor.android.logic.model.SongsDetailResponse
import com.poor.android.logic.model.SongResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ISongService {

    /**
     * 搜索歌曲
     * keywords：搜索关键字
     */
    @GET("search?")
    fun searchSongs(@Query("keywords") keywords: String): Call<SearchSongResponse>

    /**
     * 搜索歌曲详情
     * ids：歌曲id，可传入多个
     */
    @GET("song/detail?")
    fun getSongsDetail(@Query("ids") ids: String): Call<SongsDetailResponse>

    /**
     * 获取指定歌曲的Mp3Url
     * id：歌曲id
     */
    @GET("song/url?")
    fun getSongMp3Url(@Query("id") id: Int): Call<SongResponse>
}