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
        "Authorization: Bearer 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"
    )
    @POST("chat/completions")
    suspend fun preguntaChatGTP(@Body preguntaAChatGTPRequest: PreguntaAChatGTPRequest): Response<PreguntaAChatGTPResponse>
}