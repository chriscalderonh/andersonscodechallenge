package com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("sf")
    val sf: String?,
    @SerializedName("lfs")
    val lfs: List<Longform>?
)

data class Longform(
    @SerializedName("freq")
    val freq: Int?,
    @SerializedName("lf")
    val lf: String?,
    @SerializedName("vars")
    val vars: List<LfVariation>?,
    @SerializedName("since")
    val since: Int?
)

data class LfVariation(
    @SerializedName("freq")
    val freq: Int?,
    @SerializedName("lf")
    val lf: String?,
    @SerializedName("since")
    val since: Int?
)