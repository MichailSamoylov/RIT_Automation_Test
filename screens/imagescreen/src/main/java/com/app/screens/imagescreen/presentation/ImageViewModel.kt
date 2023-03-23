package com.app.screens.imagescreen.presentation

import androidx.lifecycle.ViewModel

class ImageViewModel(
	private val router: ImageRouter
): ViewModel() {

	fun navigateBack(){
		router.navigateBack()
	}
}