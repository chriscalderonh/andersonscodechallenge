package com.chriscalderonh.andersonscodechallenge.searchacronym.data.repository

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import io.reactivex.Single

interface SearchRemote {

    fun searchAcronym(shortform: String): Single<Search>
}