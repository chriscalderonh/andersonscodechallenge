package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.mapper

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.LfVariation
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Longform
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainLfVariation
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainLongform
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import javax.inject.Inject

class SearchMapper @Inject constructor(){

    fun Search.fromRemoteToDomain() = DomainSearch(
        shortform = sf,
        longforms = lfs?.fromRemoteToDomain()
    )

    private fun List<Longform>.fromRemoteToDomain(): List<DomainLongform> {
        return map { it.fromRemoteToDomain() }
    }

    private fun Longform.fromRemoteToDomain() = DomainLongform(
        frequency = freq,
        longform = lf,
        variations = vars?.fromRemoteToDomain(),
        since = since
    )

    @JvmName("fromRemoteToDomainLfVariation")
    private fun List<LfVariation>.fromRemoteToDomain(): List<DomainLfVariation> {
        return map { it.fromRemoteToDomain() }
    }

    private fun LfVariation.fromRemoteToDomain() = DomainLfVariation(
        frequency = freq,
        longform = lf,
        since = since
    )
}