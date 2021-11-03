package com.example.kotlinandroidassigment.service

import android.app.Service
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Client {
    fun getApiService(): PostApi {
        val builder = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient()
            ).build()
        return builder.create(PostApi::class.java)
    }
}