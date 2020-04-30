package com.gzeinnumer.myandroidtemplate3kt.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BaseObjectResponse<T>(@SerializedName("status") @Expose val status: String,
                                 @SerializedName("message") @Expose val message: String?,
                                 @SerializedName("data") @Expose val data: T?)