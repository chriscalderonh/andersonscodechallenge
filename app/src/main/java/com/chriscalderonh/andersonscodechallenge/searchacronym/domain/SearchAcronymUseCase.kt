package com.chriscalderonh.andersonscodechallenge.searchacronym.domain

import javax.inject.Inject

class SearchAcronymUseCase @Inject constructor(private val repository: SearchRepository) {

    fun execute(acronym: String) = repository.searchAcronym(acronym)
}