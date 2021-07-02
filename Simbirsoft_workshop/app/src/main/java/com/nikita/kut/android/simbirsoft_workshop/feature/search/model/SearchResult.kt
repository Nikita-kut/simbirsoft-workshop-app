package com.nikita.kut.android.simbirsoft_workshop.feature.search.model

import androidx.annotation.StringRes

data class SearchResult(
    val id: Long,
    @StringRes val searchResult: Int
) {
}