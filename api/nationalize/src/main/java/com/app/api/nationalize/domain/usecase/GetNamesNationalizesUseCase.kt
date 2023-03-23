package com.app.api.nationalize.domain.usecase

import com.app.api.nationalize.domain.entity.NationalizeEntity
import com.app.api.nationalize.domain.repository.NationalizeRepository

class GetNamesNationalizesUseCase(private val repository: NationalizeRepository) {

	suspend operator fun invoke(names:List<String>): List<NationalizeEntity> = repository.getNationalizes(names)
}