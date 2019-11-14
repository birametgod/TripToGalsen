package com.example.triptogalsen.models

import com.google.gson.annotations.SerializedName

data class ReligiousModel (
    val image : String,
    @SerializedName("nom")
    val name : String,
    val description : String
)