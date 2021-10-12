package com.poor.android.logic.model

data class SongsDetailResponse(val songs: List<Song>, val code: Int) {

    data class Song(val id: Int, val name: String, val al: Al)

    data class Al(val picUrl: String)
}
