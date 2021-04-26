package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.repository.SearchRemote
import io.reactivex.Single
import javax.inject.Inject

class SearchRemoteImpl @Inject constructor(private val restApi: SearchRestApi) : SearchRemote {

    override fun searchAcronym(shortform: String): Single<Search> =
        restApi.searchAcronym(shortform).map { if (it.isNullOrEmpty()) { EMPTY_SEARCH } else { it.first() } }

    companion object {
        val EMPTY_SEARCH = Search("", emptyList())
    }
}
