package com.poor.android.logic.model.db

import androidx.room.*
import com.poor.android.logic.model.cloudmusic.CloudSong

@Dao
interface SongDao {

    /************************************ 网易云音乐 start *************************************/

    @Insert
    suspend fun insertCloudSong(cloudSong: CloudSong): Long

    @Update
    suspend fun updateCloudSong(cloudSong: CloudSong)

    @Query("select * from CloudSong where songId = :songId")
    suspend fun queryCloudSongById(songId: Int): CloudSong

    @Query("select * from CloudSong where songName = :songName")
    suspend fun queryCloudSongByName(songName: String): CloudSong

    @Query("select * from CloudSong")
    suspend fun queryAllCloudSongs(): List<CloudSong>

    @Delete
    suspend fun deleteCloudSong(cloudSong: CloudSong)

    @Query("delete from CloudSong where songId = :songId")
    suspend fun deleteCloudSongById(songId: Int)

    @Query("delete from CloudSong where songName = :songName")
    suspend fun deleteCloudSongByName(songName: String)

    @Query("delete from CloudSong")
    suspend fun deleteAllCloudSong()

    /************************************ 网易云音乐 end *************************************/
}