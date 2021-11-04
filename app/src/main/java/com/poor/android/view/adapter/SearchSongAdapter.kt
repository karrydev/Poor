package com.poor.android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jeremyliao.liveeventbus.LiveEventBus
import com.poor.android.R
import com.poor.android.global.util.L
import com.poor.android.logic.model.SongType
import com.poor.android.logic.model.song.cloud.SearchCloudSongsResponse
import com.poor.android.view.fragment.SearchFragment

class SearchSongAdapter(
    private val fragment: SearchFragment,
    private val songsType: SongType,
    private val songsList: List<SearchCloudSongsResponse.SongsInfo>,
    private val songsListDefaultSize: Int
) : RecyclerView.Adapter<SearchSongAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val songName: TextView = view.findViewById(R.id.tv_song_name)
        val songType: ImageView = view.findViewById(R.id.iv_song_type)
        val songArtists: TextView = view.findViewById(R.id.tv_song_artists)
        val songMore: ImageView = view.findViewById(R.id.iv_song_more)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_song_cv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songsList[position]
        holder.songName.text = song.name
        when (songsType) {
            SongType.CloudMusic -> holder.songType.setImageResource(R.mipmap.logo_cloudmusic)
            SongType.QQMusic -> holder.songType.setImageResource(R.mipmap.logo_qqmusic)
            SongType.KugoMusic -> holder.songType.setImageResource(R.mipmap.logo_kugomusic)
            else -> holder.songType.setImageResource(0)
        }
        val artists = StringBuffer().run {
            for (artist in song.artists) {
                append(artist.name)
                if (song.artists.indexOf(artist) != song.artists.size - 1) {
                    append("/")
                }
            }
            append(" - ${song.album.name}")
            toString()
        }
        holder.songArtists.text = artists
        holder.songMore.setOnClickListener { }
        holder.itemView.setOnClickListener {
            fragment.viewModel.toPlaySong.value = true
        }
    }

    override fun getItemCount() = songsListDefaultSize
}