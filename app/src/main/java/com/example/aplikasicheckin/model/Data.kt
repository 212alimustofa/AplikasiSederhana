package com.example.aplikasicheckin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by alimustofa on 21/02/18.
 */

data class Data(
        @SerializedName("id")
        @Expose
        var id: String? = null,
        @SerializedName("lat")
        @Expose
        var lat: String? = null,
        @SerializedName("lon")
        @Expose
        var lon: String? = null,
        @SerializedName("nama")
        @Expose
        var nama: String? = null,
        @SerializedName("keterangan")
        @Expose
        var keterangan: String? = null,
        @SerializedName("kontributor")
        @Expose
        var kontributor: String? = null
)