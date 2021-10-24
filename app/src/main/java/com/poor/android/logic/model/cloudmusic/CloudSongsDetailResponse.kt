package com.poor.android.logic.model.cloudmusic

data class CloudSongsDetailResponse(val songs: List<Song>, val code: Int) {

    data class Song(val al: Al)

    data class Al(val picUrl: String)
}
