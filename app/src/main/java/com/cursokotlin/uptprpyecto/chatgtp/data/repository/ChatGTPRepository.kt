package com.cursokotlin.uptprpyecto.chatgtp.data.repository

import com.cursokotlin.uptprpyecto.chatgtp.data.api.ApiService
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPRequest
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPResponse
import com.cursokotlin.uptprpyecto.chatgtp.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatGTPRepository @Inject constructor(private val api: ApiService) {
    suspend fun preguntaChatGTP(preguntaAChatGTPRequest: PreguntaAChatGTPRequest): PreguntaAChatGTPResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.preguntaChatGTP(preguntaAChatGTPRequest)
            response.body()
        }
    }


    fun preguntaChatGTPWithFlow(preguntaAChatGTPRequest: PreguntaAChatGTPRequest): Flow<Resource<out Any?>> =
        flow {
            emit(Resource.Loading(true))
            val response = api.preguntaChatGTP(preguntaAChatGTPRequest)
            emit(Resource.Success(response.body()!! as PreguntaAChatGTPResponse))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }.flowOn(Dispatchers.IO)

    /*
    fun preguntaChatGTPWithFlow(preguntaAChatGTPRequest: PreguntaAChatGTPRequest): Flow<PreguntaAChatGTPResponse> {
        return flow {
            emit(
                api.preguntaChatGTP(preguntaAChatGTPRequest).body()!!
            )
        }.flowOn(Dispatchers.IO)
    }*/


}