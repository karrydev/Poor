package com.poor.android.logic.model.song.cloud

data class CloudSongLyric(val lrc: Lrc, val code: Int) {

    data class Lrc(val lyric: String)
}