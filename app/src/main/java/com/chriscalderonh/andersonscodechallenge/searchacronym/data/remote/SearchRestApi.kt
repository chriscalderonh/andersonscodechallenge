package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRestApi {

    @GET("software/acromine/dictionary.py")
    fun searchAcronym(@Query("sf") shortform: String): Single<Search>
}