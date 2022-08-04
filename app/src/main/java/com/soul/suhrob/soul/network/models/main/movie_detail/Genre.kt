package com.soul.suhrob.soul.network.models.main.movie_detail


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)