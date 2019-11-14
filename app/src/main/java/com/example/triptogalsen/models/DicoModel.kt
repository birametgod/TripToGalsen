package com.example.triptogalsen.models

import com.google.gson.annotations.SerializedName

data class DicoModel(
    @SerializedName("français")
    val french:String,
    val wolof: String
)