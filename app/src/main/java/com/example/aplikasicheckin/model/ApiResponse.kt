package com.example.aplikasicheckin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by alimustofa on 21/02/18.
 */
data class ApiResponse(
        @SerializedName("status")
        @Expose
        var status: String? = null,
        @SerializedName("data")
        @Expose
        var data: MutableList<Data>? = null
)