package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote

import com.chriscalderonh.andersonscodechallenge.common.net.ApiClient

class SearchApiFactory : ApiClient<SearchRestApi>() {

    init {
        restAPI = SearchRestApi::class.java
        baseURL = "http://www.nactem.ac.uk/"
    }

    fun makeRestApi(): SearchRestApi = apiService
}