package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.repository.SearchRemote
import io.reactivex.Single
import javax.inject.Inject

class SearchRemoteImpl @Inject constructor(private val restApi: SearchRestApi) : SearchRemote {

    override fun searchAcronym(shortform: String): Single<Search> =
        restApi.searchAcronym(shortform)
}
