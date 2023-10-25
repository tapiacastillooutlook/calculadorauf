package com.example.calculadorauf

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ConsumirApi {
    @GET("api/uf/{fecha}")
    fun getUf(@Path("fecha") fecha:String):Call<Json>
}