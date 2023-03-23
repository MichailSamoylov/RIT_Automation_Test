package com.app.rit_automation_test.navigation

import com.app.screens.imagescreen.presentation.ImageRouter
import com.github.terrakok.cicerone.Router

class ImageRouterImpl(private val router: Router) : ImageRouter {

	override fun navigateBack() {
		router.exit()
	}
}