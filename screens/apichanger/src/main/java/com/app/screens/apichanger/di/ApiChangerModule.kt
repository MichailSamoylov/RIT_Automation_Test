package com.app.screens.apichanger.di

import com.app.screens.apichanger.presentation.ApiChangerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiChangerModule = module {
	viewModel {(nameList:List<String>) ->
		ApiChangerViewModel(
			router = get(),
			names = nameList
		)
	}
}