package com.example.javaLearningApp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {
    @Headers("Content-Type: application/json", "Authorization: Bearer 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")
    @POST("chat/completions")
    fun getChatCompletion(@Body request: ChatRequest): Call<ChatResponse>
}

//git remote add origin https://github.com/Boradeg/OpenAiModule.git
