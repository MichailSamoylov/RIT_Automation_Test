package com.app.api.nationalize.data.repository

import com.app.api.nationalize.domain.repository.NationalizeRepository
import com.app.api.nationalize.data.datasource.NationalizeDataSource
import com.app.api.nationalize.domain.entity.NationalizeEntity

class NationalizeRepositoryImpl(private val dataSource: NationalizeDataSource) : NationalizeRepository {

	override suspend fun getNationalize(name: String): NationalizeEntity = dataSource.getNationalize(name)

	override suspend fun getNationalizes(names: List<String>): List<NationalizeEntity> = dataSource.getNationalizes(names)
}