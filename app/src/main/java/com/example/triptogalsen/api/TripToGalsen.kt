package com.example.triptogalsen.api

import com.example.triptogalsen.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val URL_LOCATION = "https://triptogalsen.firebaseio.com"

interface TripToGalsen {

    @GET("cloutLocation.json")
    fun getCloutLocation() : Call<List<CloutLocationModel>>

    @GET("sites.json")
    fun getSites() : Call<List<Sites>>

    @GET("cuisine.json")
    fun getGastro() : Call<List<GastroModel>>

    @GET("ethnis.json")
    fun getEthnis() : Call<List<EthnisModel>>

    @GET("religieux.json")
    fun getReligiousLocation() : Call<List<ReligiousModel>>

    companion object {
        fun buildRetrofit() : TripToGalsen{
            return Retrofit.Builder()
                .baseUrl(URL_LOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TripToGalsen::class.java)
        }
    }
}