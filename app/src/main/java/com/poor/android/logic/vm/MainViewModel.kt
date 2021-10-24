package com.poor.android.logic.vm

import com.poor.android.global.base.BaseViewModel
import com.poor.android.logic.repository.SongRepository

class MainViewModel : BaseViewModel() {

    private val songRepository by lazy { SongRepository }

    fun searchSongs(keywords: String) = SongRepository.searchCloudSongs(keywords)
}