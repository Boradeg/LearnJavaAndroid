package com.example.javaLearningApp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://api.openai.com/v1/"

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS) // Connection timeout
        .readTimeout(60, TimeUnit.SECONDS)    // Read timeout
        .writeTimeout(60, TimeUnit.SECONDS)   // Write timeout
        .build()
   // git repo create javaApp

    val instance: OpenAiApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OpenAiApi::class.java)
    }
}

