package com.app.api.nationalize.domain.entity

data class NationalizeEntity(
	val country: List<Country>,
	val name: String
)

data class Country(
	val country_id: String,
	val probability: String,
) {

	override fun toString(): String {
		return "country : $country_id, probability : $probability\n"
	}
}
