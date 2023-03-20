package com.dvargas.adaschool.retrofit.repository

import com.dvargas.adaschool.retrofit.network.DogsImageService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogsImageRepository {

    private const val baseUrl = "https://dog.ceo/api/"

    private val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    fun create(): DogsImageService {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(DogsImageService::class.java)
    }
}