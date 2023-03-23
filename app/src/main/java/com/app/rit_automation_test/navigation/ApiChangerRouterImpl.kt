package com.app.rit_automation_test.navigation

import com.app.screens.apichanger.presentation.ApiChangerRouter
import com.github.terrakok.cicerone.Router

class ApiChangerRouterImpl(private val router: Router):ApiChangerRouter {

	override fun navigateBack() {
		router.exit()
	}
}