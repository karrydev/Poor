package com.poor.android.logic.model.db

import androidx.room.*
import com.poor.android.logic.model.song.cloud.Song

@Dao
interface SongDao {

    /************************************ 网易云音乐 start *************************************/

    @Insert
    suspend fun insertSong(song: Song): Long

    @Update
    suspend fun updateSong(song: Song)

    @Query("select * from Song where songId = :songId")
    suspend fun querySongById(songId: Int): Song

    @Query("select * from Song where songName = :songName")
    suspend fun querySongByName(songName: String): Song

    @Query("select * from Song")
    suspend fun queryAllSongs(): List<Song>

    @Delete
    suspend fun deleteSong(song: Song)

    @Query("delete from Song where songId = :songId")
    suspend fun deleteSongById(songId: Int)

    @Query("delete from Song where songName = :songName")
    suspend fun deleteSongByName(songName: String)

    @Query("delete from Song")
    suspend fun deleteAllSong()

    /************************************ 网易云音乐 end *************************************/
}