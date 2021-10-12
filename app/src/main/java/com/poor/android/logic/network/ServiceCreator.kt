package com.poor.android.logic.network

import com.poor.android.logic.model.MusicType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL_CLOUD_MUSIC = "http://music.eleuu.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())

    fun <T> create(musicType: MusicType, serviceClass: Class<T>): T {
        when(musicType) {
            MusicType.CloudMusic -> retrofit.baseUrl(BASE_URL_CLOUD_MUSIC)
        }
        return retrofit.build().create(serviceClass)
    }

    inline fun <reified T> create(musicType: MusicType): T = create(musicType, T::class.java)
}