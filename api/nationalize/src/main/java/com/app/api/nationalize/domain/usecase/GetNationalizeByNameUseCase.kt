package com.app.api.nationalize.domain.usecase

import com.app.api.nationalize.domain.entity.NationalizeEntity
import com.app.api.nationalize.domain.repository.NationalizeRepository

class GetNationalizeByNameUseCase(private val repository: NationalizeRepository) {

	suspend operator fun invoke(name:String): NationalizeEntity = repository.getNationalize(name)
}