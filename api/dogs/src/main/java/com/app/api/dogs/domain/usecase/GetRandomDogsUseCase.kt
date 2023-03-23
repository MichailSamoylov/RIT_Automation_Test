package com.app.api.dogs.domain.usecase

import com.app.api.dogs.domain.entity.DogsEntity
import com.app.api.dogs.domain.repository.DogsRepository

class GetRandomDogsUseCase(private val repository: DogsRepository) {

	suspend operator fun invoke(): DogsEntity = repository.getDogs()
}