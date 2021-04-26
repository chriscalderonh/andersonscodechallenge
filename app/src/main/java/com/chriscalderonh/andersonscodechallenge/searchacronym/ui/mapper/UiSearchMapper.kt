package com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper

import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainLfVariation
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainLongform
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.Constants.DEFAULT_INT_VALUE
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiLfVariation
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiLongform
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiSearch
import javax.inject.Inject

class UiSearchMapper @Inject constructor() {

    fun DomainSearch.fromDomainToUi() = UiSearch(
        shortform = shortform.orEmpty(),
        longforms = longforms?.fromDomainToUi()
    )

    private fun List<DomainLongform>.fromDomainToUi(): List<UiLongform> {
        return map { it.fromDomainToUi() }
    }

    private fun DomainLongform.fromDomainToUi() = UiLongform(
        frequency = frequency ?: DEFAULT_INT_VALUE,
        longform = longform.orEmpty(),
        variations = variations?.fromDomainToUi(),
        since = since ?: DEFAULT_INT_VALUE
    )

    @JvmName("fromDomainToUiDomainLfVariation")
    private fun List<DomainLfVariation>.fromDomainToUi(): List<UiLfVariation> {
        return map { it.fromDomainToUi() }
    }

    private fun DomainLfVariation.fromDomainToUi() = UiLfVariation(
        frequency = frequency ?: DEFAULT_INT_VALUE,
        longform = longform.orEmpty(),
        since = since ?: DEFAULT_INT_VALUE
    )
}