package com.app.api.dogs.data.datasource

import com.app.api.dogs.data.api.DogsApi
import com.app.api.dogs.domain.entity.DogsEntity

class DogsDataSourceImpl(private val api:DogsApi): DogsDataSource {

	override suspend fun getDogs(): DogsEntity = api.get()
}