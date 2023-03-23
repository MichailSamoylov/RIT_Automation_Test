package com.app.screens.requestshower

import com.app.screens.requestshower.ui.RequestShowerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getRequestShowerScreen() = FragmentScreen { RequestShowerFragment.newInstance() }