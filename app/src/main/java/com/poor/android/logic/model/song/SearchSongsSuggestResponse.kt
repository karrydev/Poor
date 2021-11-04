package com.poor.android.logic.model.song

data class SearchSongsSuggestResponse(val result: Result, val code: Int) {

    data class Result(val allMatch: List<SuggestName>)

    data class SuggestName(val keyword: String)
}
