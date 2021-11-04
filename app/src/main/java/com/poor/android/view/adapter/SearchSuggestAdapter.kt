package com.poor.android.view.adapter

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poor.android.R
import com.poor.android.global.top.hideKeyboard
import com.poor.android.logic.model.song.SearchSongsSuggestResponse
import com.poor.android.view.fragment.SearchFragment

class SearchSuggestAdapter(
    private val fragment: SearchFragment,
    private val searchSuggestList: List<SearchSongsSuggestResponse.SuggestName>,
    private val textChangeWatcher: TextWatcher
) : RecyclerView.Adapter<SearchSuggestAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val suggestName: TextView = view.findViewById(R.id.search_suggest_item_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_suggest_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggest = searchSuggestList[position].keyword
        holder.suggestName.text = suggest
        holder.itemView.setOnClickListener {
            fragment.binding.searchEt.removeTextChangedListener(textChangeWatcher)
            fragment.binding.searchEt.setText(suggest)
            fragment.viewModel.searchSongs(suggest)
            fragment.binding.searchEt.clearFocus()
            fragment.hideKeyboard()
            fragment.binding.searchEt.addTextChangedListener(textChangeWatcher)
        }
    }

    override fun getItemCount() = searchSuggestList.size
}