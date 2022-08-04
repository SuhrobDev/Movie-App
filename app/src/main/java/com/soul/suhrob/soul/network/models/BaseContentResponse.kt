package com.soul.suhrob.soul.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Microstar on 19.08.2021
 */
data class BaseContentResponse<T>(
    @SerializedName("content")
    var content: List<T>
)