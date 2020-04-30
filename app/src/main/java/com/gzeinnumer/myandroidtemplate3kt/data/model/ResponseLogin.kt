package com.gzeinnumer.myandroidtemplate3kt.data.model

import com.google.gson.annotations.SerializedName

class ResponseLogin {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("website")
    var website: String? = null

}