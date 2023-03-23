package com.app.api.dogs.data.repository

import com.app.api.dogs.data.datasource.DogsDataSource
import com.app.api.dogs.domain.entity.DogsEntity
import com.app.api.dogs.domain.repository.DogsRepository

class DogsRepositoryImpl(private val dataSource: DogsDataSource):DogsRepository {

	override suspend fun getDogs(): DogsEntity  = dataSource.getDogs()
}