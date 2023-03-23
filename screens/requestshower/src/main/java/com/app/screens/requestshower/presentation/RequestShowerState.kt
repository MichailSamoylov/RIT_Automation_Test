package com.app.screens.requestshower.presentation

import com.app.api.dogs.domain.entity.DogsEntity
import com.app.api.nationalize.domain.entity.NationalizeEntity
import com.app.components.apiname.ApiNames

sealed class RequestShowerState {

	object Initial : RequestShowerState()

	object Loading : RequestShowerState()

	data class Content(
		val apiName: ApiNames,
		val apiEntityWrap: ApiEntityWrap?
	) : RequestShowerState()

	data class Error(
		val throwableMessage: String
	) : RequestShowerState()
}

sealed class ApiEntityWrap {

	data class DogsApiWrap(
		val dogsEntity: DogsEntity
	) : ApiEntityWrap()

	data class NationalizeApiWrap(
		val name: String,
		val nationalizeEntity: NationalizeEntity
	) : ApiEntityWrap()

	data class NationalizesApiWrap(
		val name: String,
		val nationalizesEntity: List<NationalizeEntity>
	) : ApiEntityWrap()

	data class CustomApiWrap(
		val url: String,
		val result: String
	) : ApiEntityWrap()
}