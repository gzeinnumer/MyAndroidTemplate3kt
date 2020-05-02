package com.gzeinnumer.myandroidtemplate3kt.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ResponsePost")
data class ResponsePost(

	@PrimaryKey
	@field:SerializedName("id")
    var id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
