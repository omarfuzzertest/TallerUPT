package com.cursokotlin.uptprpyecto.chatgtp.data.model

import com.google.gson.annotations.SerializedName


data class Choices (

    @SerializedName("message"       ) var message      : Message? = Message(),
    @SerializedName("finish_reason" ) var finishReason : String?  = null,
    @SerializedName("index"         ) var index        : Int?     = null

)