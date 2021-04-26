package com.chriscalderonh.andersonscodechallenge.searchacronym.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chriscalderonh.andersonscodechallenge.RandomValuesFactory.generateString
import com.chriscalderonh.andersonscodechallenge.common.ui.execution.FakeExecutionThread
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateDomainSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateEmptyUiSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateUiSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.SearchAcronymUseCase
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper.UiSearchMapper
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.uistates.SearchUiState
import com.chriscalderonh.andersonscodechallenge.testObserver
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    private val fakeExecutionThread = FakeExecutionThread()
    private val searchAcronymUseCase = mock<SearchAcronymUseCase>()
    private val uiSearchMapper = mock<UiSearchMapper>()
    private val viewModel =
        SearchViewModel(searchAcronymUseCase, uiSearchMapper, fakeExecutionThread)

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `given search result with data from SearchAcronymUseCase, when searchAcronym then Success`() {
        val liveDataUnderTest = viewModel.liveData().testObserver()
        val acronym = generateString()
        val domain = generateDomainSearch()
        val ui = generateUiSearch()
        stubSearchAcronymUseCase(acronym, Single.just(domain))
        stubUiSearchMapper(domain, ui)
        viewModel.searchAcronym(acronym)
        assertEquals(
            liveDataUnderTest.observedValues,
            listOf(SearchUiState.Loading, SearchUiState.SuccessGettingSearchResults(ui))
        )
    }

    @Test
    fun `given empty search result from SearchAcronymUseCase, when searchAcronym then Empty State`() {
        val liveDataUnderTest = viewModel.liveData().testObserver()
        val acronym = generateString()
        val domain = generateDomainSearch()
        val ui = generateEmptyUiSearch()
        stubSearchAcronymUseCase(acronym, Single.just(domain))
        stubUiSearchMapper(domain, ui)
        viewModel.searchAcronym(acronym)
        assertEquals(
            liveDataUnderTest.observedValues,
            listOf(SearchUiState.Loading, SearchUiState.EmptySearch)
        )
    }

    @Test
    fun `given error from SearchAcronymUseCase, when searchAcronym then Error State`() {
        val liveDataUnderTest = viewModel.liveData().testObserver()
        val acronym = generateString()
        val domain = generateDomainSearch()
        val ui = generateEmptyUiSearch()
        val error = Throwable()
        stubSearchAcronymUseCase(acronym, Single.error(error))
        stubUiSearchMapper(domain, ui)
        viewModel.searchAcronym(acronym)
        assertEquals(
            liveDataUnderTest.observedValues,
            listOf(SearchUiState.Loading, SearchUiState.ErrorGettingSearchResults(error))
        )
    }

    private fun stubSearchAcronymUseCase(acronym: String, response: Single<DomainSearch>) {
        whenever(searchAcronymUseCase.execute(acronym)).thenReturn(response)
    }

    private fun stubUiSearchMapper(domain: DomainSearch, ui: UiSearch) {
        whenever(with(uiSearchMapper) { domain.fromDomainToUi() }).thenReturn(ui)
    }
}