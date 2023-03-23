package com.app.api.nationalize.domain.repository

import com.app.api.nationalize.domain.entity.NationalizeEntity

interface NationalizeRepository {

	suspend fun getNationalize(name:String):NationalizeEntity

	suspend fun getNationalizes(names:List<String>):List<NationalizeEntity>
}