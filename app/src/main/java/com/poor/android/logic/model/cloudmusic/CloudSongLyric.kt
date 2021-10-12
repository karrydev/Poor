package com.poor.android.logic.model.cloudmusic

data class CloudSongLyric(val lrc: Lrc, val code: Int) {

    data class Lrc(val lyric: String)
}