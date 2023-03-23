package com.app.rit_automation_test.di

import com.app.rit_automation_test.navigation.ApiChangerRouterImpl
import com.app.rit_automation_test.navigation.ImageRouterImpl
import com.app.rit_automation_test.navigation.RequestShowerRouterImpl
import com.app.screens.apichanger.presentation.ApiChangerRouter
import com.app.screens.imagescreen.presentation.ImageRouter
import com.app.screens.requestshower.presentation.RequestShowerRouter
import org.koin.dsl.module

val navigationModule = module {
	factory<RequestShowerRouter> { RequestShowerRouterImpl(get()) }
	factory<ApiChangerRouter> { ApiChangerRouterImpl(get()) }
	factory<ImageRouter> { ImageRouterImpl(get()) }
}