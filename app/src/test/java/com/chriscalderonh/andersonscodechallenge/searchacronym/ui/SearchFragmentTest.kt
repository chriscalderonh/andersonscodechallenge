package com.chriscalderonh.andersonscodechallenge.searchacronym.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chriscalderonh.andersonscodechallenge.R
import com.chriscalderonh.andersonscodechallenge.common.ui.helpers.RecyclerViewMatcher
import com.chriscalderonh.andersonscodechallenge.common.ui.helpers.ViewModelUtil
import com.chriscalderonh.andersonscodechallenge.searchacronym.SearchFactory.generateUiSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper.VmSearchMapper
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.uistates.SearchUiState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<TestSearchFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer(Bundle(), R.style.Theme_AppCompat)
    }

    @Test
    fun `given loading state, when post value, then show loading component`() {
        val state = SearchUiState.Loading
        sendStateToFragment(state)

        Espresso.onView(withId(R.id.lvSearchLoading))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `given error getting search state, when post value, then show error component`() {
        val error = Throwable()
        val state = SearchUiState.ErrorGettingSearchResults(error)
        sendStateToFragment(state)

        Espresso.onView(withId(R.id.evSearchError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `given error getting search state, when click on continue button, then hide error component`() {
        val error = Throwable()
        val state = SearchUiState.ErrorGettingSearchResults(error)
        sendStateToFragment(state)

        Espresso.onView(withId(R.id.evSearchError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.btnContinue))
            .perform(click())
        Espresso.onView(withId(R.id.evSearchError))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun `given success getting empty search results, when post value, then show message and hide other components`() {
        val state = SearchUiState.EmptySearch
        sendStateToFragment(state)

        Espresso.onView(withId(R.id.lvSearchLoading))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.evSearchError))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.rvResults))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.tvEmptySearchMsg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `given success getting search results, when post value, then show results and hide other components`() {
        val searchResult = generateUiSearch()
        val state = SearchUiState.SuccessGettingSearchResults(searchResult)
        sendStateToFragment(state)

        Espresso.onView(withId(R.id.lvSearchLoading))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.evSearchError))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.tvEmptySearchMsg))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))

        searchResult.longforms?.forEachIndexed { index, result ->
            Espresso.onView(withId(R.id.rvResults)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index)
            )
            Espresso.onView(
                RecyclerViewMatcher
                    .withRecyclerView(R.id.rvResults)
                    .atPosition(index)
            ).check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(result.longform))))
        }
    }

    private fun sendStateToFragment(state: SearchUiState) {
        fragmentScenario.onFragment {
            it.liveData.postValue(state)
        }
    }

    class TestSearchFragment : SearchFragment() {
        private val viewModelFake = mock<SearchViewModel>()
        val liveData: MutableLiveData<SearchUiState> = MutableLiveData()

        override fun setupInjection() {
            stubViewModel()
            this.viewModelFactory = ViewModelUtil.createFor(viewModelFake)
            this.searchAdapter = SearchAdapter()
            this.vmSearchMapper = VmSearchMapper(context)
        }

        private fun stubViewModel() {
            whenever(viewModelFake.liveData()).thenReturn(liveData)
        }
    }
}