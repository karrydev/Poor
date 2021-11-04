package com.poor.android.logic.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.poor.android.global.base.BaseViewModel
import com.poor.android.logic.model.song.SearchSongsSuggestResponse
import com.poor.android.logic.repository.SongRepository

class MainViewModel : BaseViewModel() {

    val toPlaySong = MutableLiveData(false)
    private val searchSuggestLiveData = MutableLiveData<String>()
    private val searchSongsLiveData = MutableLiveData<String>()

    val searchSuggestList = ArrayList<SearchSongsSuggestResponse.SuggestName>()

    val suggestResultLiveData = Transformations.switchMap(searchSuggestLiveData) { keywords ->
        SongRepository.searchSongsSuggest(keywords)
    }
    val cloudSongsLiveData = Transformations.switchMap(searchSongsLiveData) { keywords ->
        SongRepository.searchCloudSongs(keywords)
    }

    fun searchSongsSuggest(keywords: String) {
        searchSuggestLiveData.value = keywords
    }

    fun searchSongs(keywords: String) {
        searchSongsLiveData.value = keywords
    }
}