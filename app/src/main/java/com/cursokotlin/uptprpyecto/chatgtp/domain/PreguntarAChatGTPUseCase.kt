package com.cursokotlin.uptprpyecto.chatgtp.domain

import com.cursokotlin.uptprpyecto.chatgtp.data.model.Messages
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPRequest
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPResponse
import com.cursokotlin.uptprpyecto.chatgtp.data.repository.ChatGTPRepository
import javax.inject.Inject

class PreguntarAChatGTPUseCase@Inject constructor(private val chatGTPRepository: ChatGTPRepository) {

    suspend operator fun invoke(pregunta:String): PreguntaAChatGTPResponse?{
        return chatGTPRepository.preguntaChatGTP(
            PreguntaAChatGTPRequest(
            model = "gpt-3.5-turbo",
            messages = arrayListOf(Messages(role = "user", content = pregunta))
        )
        )
    }
}