package com.soul.suhrob.soul.network.models.main.new_movies


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    var results: List<MovieResult>,
)