package com.app.api.nationalize.data.datasource

import com.app.api.nationalize.data.api.NationalizeApi
import com.app.api.nationalize.domain.entity.NationalizeEntity

class NationalizeDataSourceImpl(private val api: NationalizeApi) : NationalizeDataSource {

	override suspend fun getNationalize(name: String): NationalizeEntity = api.get(name)

	override suspend fun getNationalizes(names: List<String>): List<NationalizeEntity> = api.getList(names)
}