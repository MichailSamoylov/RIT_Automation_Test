package com.app.rit_automation_test.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

fun buildCicerone(): Cicerone<Router> =
	Cicerone.create()