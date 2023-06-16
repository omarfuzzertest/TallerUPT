package com.cursokotlin.uptprpyecto.chatgtp.data.repository

import com.cursokotlin.uptprpyecto.chatgtp.data.api.ApiService
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPRequest
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatGTPRepository @Inject constructor(private val api: ApiService) {
    suspend fun preguntaChatGTP(preguntaAChatGTPRequest: PreguntaAChatGTPRequest): PreguntaAChatGTPResponse?{
          return withContext(Dispatchers.IO){
                val response = api.preguntaChatGTP(preguntaAChatGTPRequest)
              response.body()
            }



    }


}