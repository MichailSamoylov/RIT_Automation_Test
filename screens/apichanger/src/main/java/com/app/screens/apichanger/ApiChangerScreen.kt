package com.app.screens.apichanger

import com.app.screens.apichanger.ui.ApiChangerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getApiChangerScreen() = FragmentScreen { ApiChangerFragment.newInstance() }