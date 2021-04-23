package com.chriscalderonh.andersonscodechallenge.searchacronym.domain

import com.chriscalderonh.andersonscodechallenge.RandomValuesFactory.generateString
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateDomainSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class SearchAcronymUseCaseTest {

    private val repository = mock<SearchRepository>()
    private val useCase = SearchAcronymUseCase(repository)

    @Test
    fun `given response, when execute, then returns data`() {
        val acronym = generateString()
        val domainSearch = generateDomainSearch()
        stubSearchAcronym(acronym, Single.just(domainSearch))

        val testObserver = useCase.execute(acronym).test()

        testObserver.assertValue(domainSearch)
    }

    private fun stubSearchAcronym(acronym: String, response: Single<DomainSearch>) {
        whenever(repository.searchAcronym(acronym)).thenReturn(response)
    }
}