package com.poor.android.logic.model.db

import androidx.room.TypeConverter
import com.poor.android.logic.model.SongType
import java.lang.StringBuilder

class CloudSongConverters {

    @TypeConverter
    fun revertType (type: Int) = when (type) {
        0 -> SongType.CloudMusic
        1 -> SongType.QQMusic
        2 -> SongType.KugoMusic
        else -> SongType.NoSong
    }

    @TypeConverter
    fun converterType (type: SongType) = when (type) {
        SongType.CloudMusic -> 0
        SongType.QQMusic -> 1
        SongType.KugoMusic -> 2
        else -> -1
    }

    @TypeConverter
    fun revertArtists (artists: String) : List<String> {
        return artists.split(',').toList()
    }

    @TypeConverter
    fun converterArtists (artists: List<String>) : String {
        return StringBuilder().run {
            artists.forEach {
                append(it)
                if (it != artists[artists.size - 1]) append(",")
            }
            toString()
        }
    }
}