package com.app.api.dogs.domain.repository

import com.app.api.dogs.domain.entity.DogsEntity

interface DogsRepository {

	suspend fun getDogs():DogsEntity
}