package com.app.screens.imagescreen.di

import com.app.screens.imagescreen.presentation.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageModule = module {
	viewModel {
		ImageViewModel(
			router = get()
		)
	}
}