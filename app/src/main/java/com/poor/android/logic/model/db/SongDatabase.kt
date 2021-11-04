package com.poor.android.logic.model.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.poor.android.logic.model.song.cloud.Song
import com.poor.android.global.base.PoorApplication

@Database(version = 1, entities = [Song::class])
@TypeConverters(CloudSongConverters::class)
abstract class SongDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    companion object {

        private var instance: SongDatabase? = null

        fun getDatabase(): SongDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                PoorApplication.context,
                SongDatabase::class.java,
                "song_database"
            ).build().apply {
                instance = this
            }
        }
    }
}