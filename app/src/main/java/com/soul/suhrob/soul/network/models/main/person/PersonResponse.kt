package com.soul.suhrob.soul.network.models.main.person


import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("results")
    var results: List<PersonResult>,
)