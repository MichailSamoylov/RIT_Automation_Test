package com.app.components.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InternetUrl {

	const val DOGS_BASE_URL = "https://dog.ceo"
	const val NATIONALIZE_BASE_URL = "https://api.nationalize.io"
}

fun dogsApiProvide(): Retrofit = Retrofit.Builder().apply {
	baseUrl(InternetUrl.DOGS_BASE_URL)
	addConverterFactory(GsonConverterFactory.create())
}.build()

fun nationalizeApiProvide(): Retrofit = Retrofit.Builder().apply {
	baseUrl(InternetUrl.NATIONALIZE_BASE_URL)
	addConverterFactory(GsonConverterFactory.create())
}.build()
