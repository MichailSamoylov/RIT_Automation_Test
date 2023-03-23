package com.app.api.dogs.di

import com.app.api.dogs.data.api.DogsApi
import com.app.api.dogs.data.datasource.DogsDataSource
import com.app.api.dogs.data.datasource.DogsDataSourceImpl
import com.app.api.dogs.data.repository.DogsRepositoryImpl
import com.app.api.dogs.domain.repository.DogsRepository
import com.app.api.dogs.domain.usecase.GetRandomDogsUseCase
import com.app.components.network.dogsApiProvide
import org.koin.dsl.module

val dogsApiModule = module{
	factory { dogsApiProvide().create(DogsApi::class.java) }
	factory<DogsDataSource> { DogsDataSourceImpl(get()) }
	factory<DogsRepository> { DogsRepositoryImpl(get()) }
	factory { GetRandomDogsUseCase(get()) }
}