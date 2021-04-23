package com.chriscalderonh.andersonscodechallenge.searchacronym.domain

import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import io.reactivex.Single

interface SearchRepository {

    fun searchAcronym(shortform: String): Single<DomainSearch>
}