package com.example.triptogalsen.models

import com.google.gson.annotations.SerializedName

data class EthnisModel (
    val image : String,
    @SerializedName("nom")
    val name : String,
    val description : String
)