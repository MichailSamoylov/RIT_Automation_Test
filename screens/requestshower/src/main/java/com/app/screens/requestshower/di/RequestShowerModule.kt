package com.app.screens.requestshower.di

import com.app.screens.requestshower.presentation.RequestShowerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val requestShowerModule = module {
	viewModel {
		RequestShowerViewModel(
			router = get(),
			dogsUseCase = get(),
			nameNationalizeUseCase = get(),
			namesNationalizesUseCase = get()
		)
	}
}