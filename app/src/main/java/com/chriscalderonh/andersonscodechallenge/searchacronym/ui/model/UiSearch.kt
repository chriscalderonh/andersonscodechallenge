package com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model

data class UiSearch(
    val shortform: String,
    val longforms: List<UiLongform>?
)

data class UiLongform(
    val frequency: Int,
    val longform: String,
    val variations: List<UiLfVariation>?,
    val since: Int
)

data class UiLfVariation(
    val frequency: Int,
    val longform: String,
    val since: Int
)