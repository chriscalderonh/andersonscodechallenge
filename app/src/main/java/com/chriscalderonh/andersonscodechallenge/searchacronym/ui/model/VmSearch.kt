package com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model

data class VmSearch(
    val longforms: List<VmLongform>?,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String,
    val isEmptySearch: Boolean = false
)

data class VmLongform(
    val frequency: String,
    val longform: String,
    val since: String
)
