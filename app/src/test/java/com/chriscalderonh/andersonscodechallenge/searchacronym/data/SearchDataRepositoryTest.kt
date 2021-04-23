package com.chriscalderonh.andersonscodechallenge.searchacronym.data

import com.chriscalderonh.andersonscodechallenge.RandomValuesFactory.generateString
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateDomainSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.mapper.SearchMapper
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model.Search
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.repository.SearchRemote
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.source.SearchSourceFactory
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class SearchDataRepositoryTest {
    private val remote = mock<SearchRemote>()
    private val factory = mock<SearchSourceFactory>()
    private val searchMapper = mock<SearchMapper>()
    private val dataRepository =
        SearchDataRepository(factory, searchMapper)

    @Before
    fun setUp() {
        stubFactoryGetRemote()
    }

    private fun stubFactoryGetRemote() {
        whenever(factory.getRemote()).thenReturn(remote)
    }

    @Test
    fun `given acronym, when searchAcronym, then returns data`() {
        val acronym = generateString()
        val search = generateSearch()
        val domainSearch = generateDomainSearch()
        stubSearchMapper(search, domainSearch)
        stubSearchAcronym(acronym, Single.just(search))

        val testObserver = dataRepository.searchAcronym(acronym).test()

        testObserver.assertValue(domainSearch)
    }

    private fun stubSearchAcronym(acronym: String, response: Single<Search>) {
        whenever(remote.searchAcronym(acronym)).thenReturn(response)
    }

    private fun stubSearchMapper(remote: Search, domain: DomainSearch) {
        whenever(with(searchMapper) { remote.fromRemoteToDomain() }).thenReturn(domain)
    }

}