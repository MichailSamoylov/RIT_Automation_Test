package com.app.screens.imagescreen

import com.app.screens.imagescreen.ui.ImageFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getImageScreen(imageUrl: String) = FragmentScreen { ImageFragment.newInstance(imageUrl) }