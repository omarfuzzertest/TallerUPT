package com.cursokotlin.uptprpyecto.chatgtp.data.model

import com.cursokotlin.uptprpyecto.chatgtp.data.model.Messages
import com.google.gson.annotations.SerializedName


data class PreguntaAChatGTPRequest (

    @SerializedName("model"    ) var model    : String?             = null,
    @SerializedName("messages" ) var messages : ArrayList<Messages> = arrayListOf()

)