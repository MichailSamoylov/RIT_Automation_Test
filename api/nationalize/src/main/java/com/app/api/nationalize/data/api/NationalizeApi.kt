package com.app.api.nationalize.data.api

import com.app.api.nationalize.domain.entity.NationalizeEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NationalizeApi {

	@GET("/")
	suspend fun get(@Query("name") name:String): NationalizeEntity

	@GET("/")
	suspend fun getList(
		@Query("name[]") names:List<String>,
	): List<NationalizeEntity>
}