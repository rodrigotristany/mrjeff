package com.rodrigotristany.mrjeff.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Status(
    @SerializedName("message")
    @Expose
    val message: String?,

    @SerializedName("value")
    @Expose
    val value: Int
)