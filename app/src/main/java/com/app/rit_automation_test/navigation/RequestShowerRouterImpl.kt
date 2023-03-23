package com.app.rit_automation_test.navigation

import com.app.screens.apichanger.getApiChangerScreen
import com.app.screens.imagescreen.getImageScreen
import com.app.screens.requestshower.presentation.RequestShowerRouter
import com.github.terrakok.cicerone.Router

class RequestShowerRouterImpl(private val router: Router) : RequestShowerRouter {

	override fun navigateToImageScreen(imageUrl: String) {
		router.navigateTo(getImageScreen(imageUrl))
	}

	override fun navigateToApis() {
		router.navigateTo(getApiChangerScreen())
	}
}