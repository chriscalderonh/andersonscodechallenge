package com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper

import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateDomainSearch
import org.junit.Assert.assertEquals
import org.junit.Test

class UiSearchMapperTest {
    private val mapper = UiSearchMapper()

    @Test
    fun `given DomainSearch, when fromDomainToUi, then UiSearch`() {

        val domainSearch = generateDomainSearch()

        val uiSearch = with(mapper) {
            domainSearch.fromDomainToUi()
        }

        assertEquals("shortform", domainSearch.shortform, uiSearch.shortform)
        domainSearch.longforms?.forEachIndexed { index, longform ->
            assertEquals("frequency", longform.frequency, uiSearch.longforms?.get(index)?.frequency)
            assertEquals("longform", longform.longform, uiSearch.longforms?.get(index)?.longform)
            assertEquals("since", longform.since, uiSearch.longforms?.get(index)?.since)
            longform.variations?.forEachIndexed { index2, lfVariation ->
                val domainVariations = uiSearch.longforms?.get(index)?.variations
                assertEquals(
                    "frequency",
                    lfVariation.frequency,
                    domainVariations?.get(index2)?.frequency
                )
                assertEquals("longform", lfVariation.longform, domainVariations?.get(index2)?.longform)
                assertEquals("since", lfVariation.since, domainVariations?.get(index2)?.since)
            }
        }
    }

}