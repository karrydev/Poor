package com.poor.android.logic.model

data class SongResponse(val data: List<Song>, val code: Int) {

    data class Song(val url: String)
}