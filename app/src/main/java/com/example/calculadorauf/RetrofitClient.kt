package com.example.calculadorauf

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retro = Retrofit.Builder()
        .baseUrl("https://mindicador.cl")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val consumirApi = retro.create(ConsumirApi::class.java)
}

