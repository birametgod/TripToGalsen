package com.example.triptogalsen.models

import com.google.gson.annotations.SerializedName

data class CloutLocationModel(
    @SerializedName("lieu")
    val location: String,
    val image : String,
    @SerializedName("nom")
    val name : String,
    val type : String
    )