package com.poor.android.logic.model.cloudmusic

data class CloudSongResponse(val data: List<Song>, val code: Int) {

    data class Song(val url: String)
}