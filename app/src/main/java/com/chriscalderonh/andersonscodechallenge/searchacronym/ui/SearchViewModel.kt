package com.chriscalderonh.andersonscodechallenge.searchacronym.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chriscalderonh.andersonscodechallenge.common.ui.execution.ExecutionThread
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.SearchAcronymUseCase
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model.DomainSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper.UiSearchMapper
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.uistates.SearchUiState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchAcronymUseCase: SearchAcronymUseCase,
    private val uiSearchMapper: UiSearchMapper,
    private val executionThread: ExecutionThread
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val liveDataUiState = MutableLiveData<SearchUiState>()

    fun liveData(): LiveData<SearchUiState> = liveDataUiState

    fun searchAcronym(acronym: String) {
        disposable.add(
            searchAcronymUseCase.execute(acronym)
                .subscribeOn(executionThread.schedulerForSubscribing())
                .observeOn(executionThread.schedulerForObserving())
                .doOnSubscribe {
                    liveDataUiState.postValue(SearchUiState.Loading)
                }
                .subscribeWith(object : DisposableSingleObserver<DomainSearch>() {
                    override fun onError(e: Throwable) {
                        liveDataUiState.postValue(SearchUiState.ErrorGettingSearchResults(e))
                    }

                    override fun onSuccess(value: DomainSearch) {
                        val searchResult = with(uiSearchMapper) {
                            value.fromDomainToUi()
                        }
                        if (searchResult.longforms.isNullOrEmpty()) {
                            liveDataUiState.postValue(SearchUiState.EmptySearch)
                        } else {
                            liveDataUiState.postValue(
                                SearchUiState.SuccessGettingSearchResults(
                                    searchResult
                                )
                            )
                        }
                    }
                })
        )
    }
}