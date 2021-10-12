package com.poor.android.logic.model.cloudmusic

import com.google.gson.annotations.SerializedName

data class SearchCloudSongsResponse(val result: Result, val code: Int) {

    data class Result(val songs: List<SongDetail>, val songCount: Int)

    data class SongDetail(
        val id: Int,
        val name: String,
        val artists: List<Artist>,
        val album: Album
    )

    data class Artist(val name: String, @SerializedName("imglvlUrl") val imgUrl: String)

    data class Album(val id: Int, val name: String, val artist: Artist)
}
