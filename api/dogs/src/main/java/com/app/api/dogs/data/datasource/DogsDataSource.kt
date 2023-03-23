package com.app.api.dogs.data.datasource

import com.app.api.dogs.domain.entity.DogsEntity

interface DogsDataSource {

	suspend fun getDogs():DogsEntity
}