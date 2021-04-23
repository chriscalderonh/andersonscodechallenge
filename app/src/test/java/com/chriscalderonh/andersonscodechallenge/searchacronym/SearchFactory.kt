package com.chriscalderonh.andersonscodechallenge.searchacronym

import com.chriscalderonh.andersonscodechallenge.RandomValuesFactory.generateInt
import com.chriscalderonh.andersonscodechallenge.RandomValuesFactory.generateString
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.LfVariation
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Longform
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainLfVariation
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainLongform
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch

object SearchFactory {

    fun generateSearch() = Search(
        generateString(),
        generateLfs()
    )

    private fun generateLfs() = (0 .. 10).map { generateLongform() }

    private fun generateLongform() = Longform(
        generateInt(),
        generateString(),
        generateVars(),
        generateInt()
    )

    private fun generateVars() = (0 .. 10).map { generateLfVariation() }

    private fun generateLfVariation() = LfVariation(
        generateInt(),
        generateString(),
        generateInt()
    )

    fun generateDomainSearch() = DomainSearch(
        generateString(),
        generateLongforms()
    )

    private fun generateLongforms() = (0 .. 10).map { generateDomainLongform() }

    private fun generateDomainLongform() = DomainLongform(
        generateInt(),
        generateString(),
        generateVariations(),
        generateInt()
    )

    private fun generateVariations() = (0 .. 10).map { generateDomainLfVariation() }

    private fun generateDomainLfVariation() = DomainLfVariation(
        generateInt(),
        generateString(),
        generateInt()
    )
}