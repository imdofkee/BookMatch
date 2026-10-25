package com.dofkee.bookme.data.remote.retrofit

import com.dofkee.bookme.data.remote.MifAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// аналог localhost
val API_URL = Retrofit.Builder().baseUrl("http://10.0.2.2/")
    .addConverterFactory(GsonConverterFactory.create()) //для JSON
    .build()

val mif_api: MifAPI = API_URL.create(MifAPI::class.java)