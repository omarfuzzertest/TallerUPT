package com.cursokotlin.uptprpyecto.chatgtp.data.model

import com.google.gson.annotations.SerializedName

data class Messages (

    @SerializedName("role"    ) var role    : String? = null,
    @SerializedName("content" ) var content : String? = null

)