package com.chriscalderonh.andersonscodechallenge.searchacronym.data

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.mapper.SearchMapper
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.source.SearchSourceFactory
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.SearchRepository
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import io.reactivex.Single
import javax.inject.Inject

class SearchDataRepository @Inject constructor(
    private val factory: SearchSourceFactory,
    private val searchMapper: SearchMapper
) : SearchRepository {

    override fun searchAcronym(shortform: String): Single<DomainSearch> = factory
        .getRemote()
        .searchAcronym(shortform)
        .map { results ->
            with(searchMapper) {
                results.fromRemoteToDomain()
            }
        }
}
