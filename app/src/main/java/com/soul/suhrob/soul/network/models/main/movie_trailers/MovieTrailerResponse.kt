package com.soul.suhrob.soul.network.models.main.movie_trailers


import com.google.gson.annotations.SerializedName

data class MovieTrailerResponse(
    @SerializedName("results")
    var results: List<Result>
)