package com.chriscalderonh.andersonscodechallenge.searchacronym.ui

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.chriscalderonh.andersonscodechallenge.MainApplication
import com.chriscalderonh.andersonscodechallenge.R
import com.chriscalderonh.andersonscodechallenge.databinding.FragmentSearchBinding
import com.chriscalderonh.andersonscodechallenge.searchacronym.di.DaggerSearchComponent
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper.VmSearchMapper
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.uistates.SearchUiState
import javax.inject.Inject

open class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var vmSearchMapper: VmSearchMapper

    private val searchViewModel: SearchViewModel? by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
        observeUiStates()
    }

    protected open fun setupInjection() {
        DaggerSearchComponent.builder()
            .appComponent(MainApplication.appComponent)
            .build()
            .inject(this)
    }

    private fun observeUiStates() {
        searchViewModel?.liveData()
            ?.observe(this, { uiState ->
                uiState?.let { renderUiStates(it) }
            })
    }

    private fun renderUiStates(uiState: SearchUiState) {
        val vmSearch = vmSearchMapper.uiStateToVmSearch(
            uiState.isLoading,
            uiState.searchResult?.longforms,
            uiState.isEmptySearch,
            uiState.isError,
            uiState.error
        )
        binding.viewModel = vmSearch
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupAdapter()
        setupListeners()
        return binding.root
    }

    private fun setupAdapter() {
        context?.let {
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            AppCompatResources.getDrawable(it, R.drawable.rv_line_divider)?.let { drawable ->
                itemDecoration.setDrawable(drawable)
                binding.rvResults.addItemDecoration(itemDecoration)
            }
        }
        binding.rvResults.adapter = searchAdapter
    }

    private fun setupListeners() {
        setSearchListener()
        setErrorViewListener()
    }

    private fun setSearchListener() {
        binding.etSearchText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                context?.let { hideKeyboard(it, binding.etSearchText) }
                val searchText = binding.etSearchText.text.toString()
                searchViewModel?.searchAcronym(searchText)
                true
            }
            false
        }
    }

    private fun setErrorViewListener() {
        binding.evSearchError.setClickListener  {
            binding.evSearchError.visibility = View.GONE
        }
    }

    private fun hideKeyboard(context: Context, view: View) {
        try {
            val keyboard =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
        }
    }
}