package com.poor.android.logic.model.cloudmusic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poor.android.logic.model.MusicType

@Entity
data class CloudSong(
    @PrimaryKey var songId: Int,
    var songName: String,
    var artists: List<String>,
    var albumName: String,
    var albumPic: String,
    var mp3Url: String,
    var lyric: String
) {
    var type = MusicType.CloudMusic
}
