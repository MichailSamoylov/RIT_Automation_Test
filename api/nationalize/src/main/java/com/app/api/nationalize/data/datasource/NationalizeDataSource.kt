package com.app.api.nationalize.data.datasource

import com.app.api.nationalize.domain.entity.NationalizeEntity

interface NationalizeDataSource {

	suspend fun getNationalize(name:String):NationalizeEntity

	suspend fun getNationalizes(names:List<String>):List<NationalizeEntity>
}