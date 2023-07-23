package com.example.n2

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//todo : device-ip should be found?
const val BASE_URL = "http://192.168.43.32:8081/"

class InitRetrofit {
    private var retrofit: Retrofit? = null


    fun getClient(): Retrofit{
        val client = OkHttpClient.Builder().build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

}
