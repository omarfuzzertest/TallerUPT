package com.cursokotlin.uptprpyecto.chatgtp.data.api

import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPRequest
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {




    @Headers(
        "Content-Type: application/json",
        "X-RapidAPI-Key: 35ac5be120msh2ebd9719d65221ep1de8fejsn99a7e12abc0c",
        "X-RapidAPI-Host: openai80.p.rapidapi.com"
    )
    @POST("chat/completions")
    suspend fun preguntaChatGTP(@Body preguntaAChatGTPRequest: PreguntaAChatGTPRequest): Response<PreguntaAChatGTPResponse>
}