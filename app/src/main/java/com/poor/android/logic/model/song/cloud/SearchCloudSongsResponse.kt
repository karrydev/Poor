package com.poor.android.logic.model.song.cloud

data class SearchCloudSongsResponse(val result: Result, val code: Int) {

    data class Result(val songs: List<SongsInfo>, val songCount: Int)

    data class SongsInfo(
        val id: Int,
        val name: String,
        val artists: List<Artist>,
        val album: Album
    )

    data class Artist(val name: String)

    data class Album(val id: Int, val name: String)
}
