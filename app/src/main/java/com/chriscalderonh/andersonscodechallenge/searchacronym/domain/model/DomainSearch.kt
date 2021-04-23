package com.chriscalderonh.andersonscodechallenge.searchacronym.domain.model

data class DomainSearch(
    val shortform: String?,
    val longforms: List<DomainLongform>?
)

data class DomainLongform(
    val frequency: Int?,
    val longform: String?,
    val variations: List<DomainLfVariation>?,
    val since: Int?
)

data class DomainLfVariation(
    val frequency: Int?,
    val longform: String?,
    val since: Int?
)