package com.poor.android.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.poor.android.R
import com.poor.android.databinding.FragmentSearchBinding
import com.poor.android.databinding.ShowCardBinding
import com.poor.android.global.base.BaseFragment
import com.poor.android.global.top.hideKeyboard
import com.poor.android.global.top.isSoftInputShow
import com.poor.android.logic.model.SongType
import com.poor.android.logic.model.song.cloud.SearchCloudSongsResponse
import com.poor.android.logic.vm.MainViewModel
import com.poor.android.view.activity.PlayerActivity
import com.poor.android.view.adapter.SearchSongAdapter
import com.poor.android.view.adapter.SearchSuggestAdapter

class SearchFragment : BaseFragment<MainViewModel, FragmentSearchBinding>() {

    private lateinit var searchSuggestAdapter: SearchSuggestAdapter
    private lateinit var searchSongAdapter: SearchSongAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun initView() {
        // 返回按钮
        binding.searchBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_searchFragment_to_navigation_find)
        }
        // 搜索输入框
        binding.searchClear.setOnClickListener {
            binding.searchEt.text.clear()
            binding.searchEt.requestFocus()
            binding.searchEt.isSoftInputShow = true
        }
        binding.searchEt.apply {
            requestFocus()
            isSoftInputShow = true
        }
        val textChangeWatcher = binding.searchEt.addTextChangedListener { editable ->
            val keyword = editable.toString()
            if (keyword.isNotEmpty()) {
                binding.searchClear.isVisible = true
                viewModel.searchSongsSuggest(keyword)
            } else {
                binding.searchClear.isVisible = false
                binding.searchSuggestRv.isVisible = false
            }
        }
        // 搜索结果推荐
        binding.searchSuggestRv.layoutManager = LinearLayoutManager(context)
        searchSuggestAdapter = SearchSuggestAdapter(this, viewModel.searchSuggestList, textChangeWatcher)
        binding.searchSuggestRv.adapter = searchSuggestAdapter
        viewModel.suggestResultLiveData.observe(this) {
            val result = it.getOrNull()
            if (result != null) {
                binding.searchSuggestRv.isVisible = true
                viewModel.searchSuggestList.clear()
                viewModel.searchSuggestList.addAll(result.allMatch)
                searchSuggestAdapter.notifyDataSetChanged()
            } else {
                it.exceptionOrNull()?.printStackTrace()
            }
        }
        // 搜索回车查询
        binding.searchEt.setOnEditorActionListener { v, _, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                // 捕捉软键盘回车事件
                binding.searchEt.clearFocus()
                viewModel.searchSongs(v.text.toString())
                hideKeyboard()
                return@setOnEditorActionListener true
            }
            false
        }
        // 监听搜索结果
        viewModel.cloudSongsLiveData.observe(this) { result ->
            binding.searchSuggestRv.isVisible = false
            result.getOrNull()?.let { initSongCard(binding.searchCloudList, it, SongType.CloudMusic) }
        }
    }

    override fun initData() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initSongCard(card: ShowCardBinding, result: SearchCloudSongsResponse.Result, songType: SongType) {
        card.apply {
            itemView.apply {
                isVisible = true
                background = when (songType) {
                    SongType.CloudMusic -> resources.getDrawable(R.color.red, null)
                    SongType.QQMusic -> resources.getDrawable(R.color.green, null)
                    SongType.KugoMusic -> resources.getDrawable(R.color.blue, null)
                    else -> resources.getDrawable(R.color.black, null)
                }
            }
            itemTitle.text = when (songType) {
                SongType.CloudMusic -> getString(R.string.cloud_music)
                SongType.QQMusic -> getString(R.string.qq_music)
                SongType.KugoMusic -> getString(R.string.kugo_music)
                else -> ""
            }
            itemMore.setOnClickListener {
                // TODO
            }
            itemRv.layoutManager = LinearLayoutManager(context)
            searchSongAdapter = SearchSongAdapter(this@SearchFragment, songType, result.songs, 5)
            itemRv.adapter = searchSongAdapter
            showCard.isVisible = true
        }
    }

    override val layoutId = R.layout.fragment_search

    override fun getViewModel() = MainViewModel::class.java
}