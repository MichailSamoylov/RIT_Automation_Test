package com.app.rit_automation_test.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.rit_automation_test.R
import com.app.screens.requestshower.getRequestShowerScreen
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

	private val route: Router by inject()
	private val navigateHolder: NavigatorHolder by inject()
	private val navigator = AppNavigator(this, R.id.container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		route.newRootScreen(getRequestShowerScreen())
	}

	override fun onResume() {
		super.onResume()
		navigateHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		navigateHolder.removeNavigator()
	}
}