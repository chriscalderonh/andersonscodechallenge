package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote

import com.chriscalderonh.andersonscodechallenge.RandomValuesFactory.generateString
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class SearchRemoteImplTest {
    private val restApi = mock<SearchRestApi>()
    private val searchRemoteImpl = SearchRemoteImpl(restApi)

    @Test
    fun `given acronym, when searchAcronym, then returns data`() {
        val acronym = generateString()
        val search = generateSearch()
        stubRestApiSearchAcronym(acronym, Single.just(arrayListOf(search)))

        val testObserver = searchRemoteImpl.searchAcronym(acronym).test()

        testObserver.assertValue(search)
    }

    private fun stubRestApiSearchAcronym(acronym: String, response: Single<List<Search>>) {
        whenever(restApi.searchAcronym(acronym)).thenReturn(response)
    }
}