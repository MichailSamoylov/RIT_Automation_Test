package com.app.api.dogs.data.api

import com.app.api.dogs.domain.entity.DogsEntity
import retrofit2.http.GET

interface DogsApi {

	@GET("/api/breeds/image/random")
	suspend fun get(): DogsEntity
}