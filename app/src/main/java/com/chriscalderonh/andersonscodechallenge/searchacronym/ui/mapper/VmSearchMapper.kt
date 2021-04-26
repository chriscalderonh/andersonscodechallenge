package com.chriscalderonh.andersonscodechallenge.searchacronym.ui.mapper

import android.content.Context
import com.chriscalderonh.andersonscodechallenge.R
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiLongform
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiSearch
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.VmLongform
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.VmSearch
import java.net.ConnectException
import javax.inject.Inject

class VmSearchMapper @Inject constructor(private val context: Context?) {

    fun uiStateToVmSearch(
        isLoading: Boolean,
        longforms: List<UiLongform>?,
        isEmptySearch: Boolean,
        isError: Boolean,
        error: Throwable?
    ): VmSearch {
        val errorMsg = errorMsgProvider(error)
        return VmSearch(longforms?.fromUiToVm(), isLoading, isError, errorMsg, isEmptySearch)
    }

    private fun List<UiLongform>.fromUiToVm(): List<VmLongform> {
        return map { it.fromUiToVm() }
    }

    private fun UiLongform.fromUiToVm() = VmLongform(
        frequency = frequency.toString(),
        longform = longform,
        since = since.toString()
    )

    private fun errorMsgProvider(error: Throwable?): String {
        var errorMsg: String = ""
        context?.let {
            errorMsg = when (error) {
                is ConnectException -> it.getString(R.string.sorry_there_seems_to_be_a_problem_with_your_connection)
                else -> it.getString(R.string.sorry_an_error_occurred)
            }
        }
        return errorMsg
    }
}