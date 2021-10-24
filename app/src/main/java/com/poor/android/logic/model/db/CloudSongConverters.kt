package com.poor.android.logic.model.db

import androidx.room.TypeConverter
import com.poor.android.logic.model.MusicType
import java.lang.StringBuilder

class CloudSongConverters {

    @TypeConverter
    fun revertType (type: Int) = when (type) {
        0 -> MusicType.CloudMusic
        1 -> MusicType.QQMusic
        2 -> MusicType.KugoMusic
        else -> MusicType.NoMusic
    }

    @TypeConverter
    fun converterType (type: MusicType) = when (type) {
        MusicType.CloudMusic -> 0
        MusicType.QQMusic -> 1
        MusicType.KugoMusic -> 2
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