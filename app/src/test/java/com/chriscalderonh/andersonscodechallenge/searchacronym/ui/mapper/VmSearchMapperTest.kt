package com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper

import com.chriscalderonh.andersonscodechallenge.RandomValuesFactory.generateBoolean
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateUiLongforms
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateUiSearch
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Test

class VmSearchMapperTest {
    private val mapper = VmSearchMapper(null)

    @Test
    fun `given UiSearch, when fromUiToVm, then VmSearch`() {

        val isLoading = generateBoolean()
        val isError = generateBoolean()
        val isEmptySearch = generateBoolean()
        val error = Throwable()
        val longforms = generateUiLongforms()

        val vmSearch = mapper.uiStateToVmSearch(isLoading, longforms ,isEmptySearch, isError, error)

        Assert.assertEquals("isLoading", isLoading, vmSearch.isLoading)
        Assert.assertEquals("isError", isError, vmSearch.isError)
        Assert.assertEquals("isEmptySearch", isEmptySearch, vmSearch.isEmptySearch)
        longforms.forEachIndexed { index, longform ->
            Assert.assertEquals(
                "frequency",
                longform.frequency.toString(),
                vmSearch.longforms?.get(index)?.frequency
            )
            Assert.assertEquals(
                "longform",
                longform.longform,
                vmSearch.longforms?.get(index)?.longform
            )
            Assert.assertEquals(
                "since",
                longform.since.toString(),
                vmSearch.longforms?.get(index)?.since
            )
        }
    }

}