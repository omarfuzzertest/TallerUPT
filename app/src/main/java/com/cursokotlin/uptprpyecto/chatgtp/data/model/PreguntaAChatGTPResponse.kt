package com.cursokotlin.uptprpyecto.chatgtp.data.model

import com.google.gson.annotations.SerializedName

data class PreguntaAChatGTPResponse (

    @SerializedName("id"      ) var id      : String?            = null,
    @SerializedName("object"  ) var objeto  : String?            = null,
    @SerializedName("created" ) var created : Int?               = null,
    @SerializedName("model"   ) var model   : String?            = null,
    @SerializedName("usage"   ) var usage   : Usage?             = Usage(),
    @SerializedName("choices" ) var choices : ArrayList<Choices> = arrayListOf()

)