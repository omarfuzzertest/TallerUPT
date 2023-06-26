package com.cursokotlin.uptprpyecto.chatgtp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.uptprpyecto.chatgtp.data.model.PreguntaAChatGTPResponse
import com.cursokotlin.uptprpyecto.chatgtp.data.util.Resource
import com.cursokotlin.uptprpyecto.chatgtp.domain.PreguntarAChatGTPUseCase
import com.cursokotlin.uptprpyecto.chatgtp.domain.PreguntarAChatGTPWithFlowUSeCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGTPViewModel @Inject constructor(
    private val preguntarAChatGTPUseCase: PreguntarAChatGTPUseCase,
    private val preguntarAChatGTPWithFlowUSeCase: PreguntarAChatGTPWithFlowUSeCase
) : ViewModel() {


    private val _pregunta = MutableLiveData<String>()
    val pregunta: LiveData<String> = _pregunta

    private val _isBotonEnabled = MutableLiveData<Boolean>()
    val isBotonEnabled: LiveData<Boolean> = _isBotonEnabled


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _respuestaChat = MutableLiveData<String>()
    val respuestaChat: LiveData<String> = _respuestaChat

    fun onPreguntaChanged(pregunta: String) {
        _pregunta.value = pregunta
        _isBotonEnabled.value = verificarBotonEnabled(pregunta)
    }

    private fun verificarBotonEnabled(pregunta: String): Boolean = pregunta.length > 5

    fun onPreguntarToChatGTP() = viewModelScope.launch {
        _isLoading.value = true
        val resultado = preguntarAChatGTPUseCase(_pregunta.value ?: "")
        if (resultado != null) {
            _respuestaChat.value = resultado.choices.first().message?.content
        }
        _isLoading.value = false
    }


    fun onPreguntarToChatGTPWithFlow() = viewModelScope.launch {
        preguntarAChatGTPWithFlowUSeCase(_pregunta.value ?: "").collect{
            when(it){
                is Resource.Error -> {
                    _isLoading.value = false
                    _respuestaChat.value = "Error al consumir el api"
                }
                is Resource.Loading -> {_isLoading.value = true}
                is Resource.Success -> {
                    _isLoading.value = false
                    _respuestaChat.value = (it.data as PreguntaAChatGTPResponse).choices.first().message?.content
                }
            }
        }
    }

}