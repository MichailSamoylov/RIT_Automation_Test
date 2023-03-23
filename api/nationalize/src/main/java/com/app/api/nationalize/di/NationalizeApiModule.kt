package com.app.api.nationalize.di

import com.app.api.nationalize.data.api.NationalizeApi
import com.app.api.nationalize.data.datasource.NationalizeDataSource
import com.app.api.nationalize.data.datasource.NationalizeDataSourceImpl
import com.app.api.nationalize.data.repository.NationalizeRepositoryImpl
import com.app.api.nationalize.domain.repository.NationalizeRepository
import com.app.api.nationalize.domain.usecase.GetNamesNationalizesUseCase
import com.app.api.nationalize.domain.usecase.GetNationalizeByNameUseCase
import com.app.components.network.nationalizeApiProvide
import org.koin.dsl.module

val nationalizeApiModule = module {
	factory { nationalizeApiProvide().create(NationalizeApi::class.java) }
	factory<NationalizeDataSource> { NationalizeDataSourceImpl(get()) }
	factory<NationalizeRepository> { NationalizeRepositoryImpl(get()) }
	factory { GetNationalizeByNameUseCase(get()) }
	factory { GetNamesNationalizesUseCase(get()) }
}