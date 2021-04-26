package com.chriscalderonh.andersonscodechallenge.searchacronym.ui.uistates

import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiSearch

sealed class SearchUiState(val isLoading: Boolean = false,
                           val searchResult: UiSearch? = null,
                           val isEmptySearch: Boolean = false,
                           val isError: Boolean = false,
                           val error: Throwable? = null) {

    object Loading: SearchUiState(
        isLoading = true
    )

    data class SuccessGettingSearchResults(val uiSearch: UiSearch): SearchUiState(
        searchResult = uiSearch
    )

    object EmptySearch: SearchUiState(
        isEmptySearch = true
    )

    data class ErrorGettingSearchResults(val e: Throwable?): SearchUiState(
        error = e,
        isError = true
    )
}