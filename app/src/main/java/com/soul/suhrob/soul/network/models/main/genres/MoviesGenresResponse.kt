package com.soul.suhrob.soul.network.models.main.genres


import com.google.gson.annotations.SerializedName

data class MoviesGenresResponse(
    @SerializedName("genres")
    var genres: List<Genre>
)