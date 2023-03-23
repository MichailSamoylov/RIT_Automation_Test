package com.app.rit_automation_test

import android.app.Application
import com.app.api.dogs.di.dogsApiModule
import com.app.api.nationalize.di.nationalizeApiModule
import com.app.rit_automation_test.di.ciceroneModule
import com.app.rit_automation_test.di.navigationModule
import com.app.screens.apichanger.di.apiChangerModule
import com.app.screens.imagescreen.di.imageModule
import com.app.screens.requestshower.di.requestShowerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)

			modules(
				ciceroneModule,
				navigationModule,
				requestShowerModule,
				dogsApiModule,
				nationalizeApiModule,
				apiChangerModule,
				imageModule,
			)
		}
	}
}