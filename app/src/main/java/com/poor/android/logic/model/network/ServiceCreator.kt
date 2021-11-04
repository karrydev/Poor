package com.poor.android.logic.model.network

import com.poor.android.logic.model.SongType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL_CLOUD_SONG = "http://music.eleuu.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())

    fun <T> create(songType: SongType, serviceClass: Class<T>): T {
        when(songType) {
            SongType.CloudMusic -> retrofit.baseUrl(BASE_URL_CLOUD_SONG)
        }
        return retrofit.build().create(serviceClass)
    }

    inline fun <reified T> create(songType: SongType): T = create(songType, T::class.java)
}