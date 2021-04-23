package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.mapper

import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateSearch
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchMapperTest {
    private val mapper = SearchMapper()

    @Test
    fun `given Search, when fromRemoteToDomain, then DomainSearch`() {

        val search = generateSearch()

        val domainSearch = with(mapper) {
            search.fromRemoteToDomain()
        }

        assertEquals("shortform", search.sf, domainSearch.shortform)
        search.lfs?.forEachIndexed { index, longform ->
            assertEquals("frequency", longform.freq, domainSearch.longforms?.get(index)?.frequency)
            assertEquals("longform", longform.lf, domainSearch.longforms?.get(index)?.longform)
            assertEquals("since", longform.since, domainSearch.longforms?.get(index)?.since)
            longform.vars?.forEachIndexed { index2, lfVariation ->
                val domainVariations = domainSearch.longforms?.get(index)?.variations
                assertEquals("frequency", lfVariation.freq, domainVariations?.get(index2)?.frequency)
                assertEquals("longform", lfVariation.lf, domainVariations?.get(index2)?.longform)
                assertEquals("since", lfVariation.since, domainVariations?.get(index2)?.since)
            }
        }
    }

}