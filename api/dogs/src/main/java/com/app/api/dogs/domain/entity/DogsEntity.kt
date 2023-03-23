package com.app.api.dogs.domain.entity

import com.google.gson.annotations.SerializedName

data class DogsEntity(
	@SerializedName("message")
	val dogsImage:String,
	val status:String
)
