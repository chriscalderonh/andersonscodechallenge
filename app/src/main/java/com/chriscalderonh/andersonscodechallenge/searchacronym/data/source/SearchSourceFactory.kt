package com.chriscalderonh.andersonscodechallenge.searchacronym.data.source

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.repository.SearchRemote
import javax.inject.Inject

class SearchSourceFactory @Inject constructor(
    private val searchRemote: SearchRemote
) {

    fun getRemote(): SearchRemote = searchRemote
}