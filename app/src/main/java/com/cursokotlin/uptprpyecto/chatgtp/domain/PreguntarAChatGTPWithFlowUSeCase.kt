package com.cursokotlin.uptprpyecto.chatgtp.domain

import com.cursokotlin.uptprpyecto.chatgtp.data.model.Messages
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPRequest
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPResponse
import com.cursokotlin.uptprpyecto.chatgtp.data.repository.ChatGTPRepository
import com.cursokotlin.uptprpyecto.chatgtp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreguntarAChatGTPWithFlowUSeCase @Inject constructor(private val chatGTPRepository: ChatGTPRepository) {
    operator fun invoke(pregunta:String) : Flow<Resource<out Any?>> {
        return chatGTPRepository.preguntaChatGTPWithFlow(
            PreguntaAChatGTPRequest(
                model = "gpt-3.5-turbo",
                messages = arrayListOf(Messages(role = "user", content = pregunta))
            )
        )
    }
}