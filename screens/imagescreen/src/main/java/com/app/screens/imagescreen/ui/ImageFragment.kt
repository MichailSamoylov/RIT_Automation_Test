package com.app.screens.imagescreen.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.app.screens.imagescreen.R
import com.app.screens.imagescreen.presentation.ImageViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

const val IMAGE_URL_KEY = "IMAGE_URL_KEY"

var ImageFragment.imageUrl: String
	get() = this.arguments?.getString(IMAGE_URL_KEY) ?: throw IllegalArgumentException("Arguments can't be null")
	set(value) {
		arguments = arguments?.also { it.putString(IMAGE_URL_KEY, value) } ?: bundleOf(IMAGE_URL_KEY to value)
	}

class ImageFragment : Fragment(R.layout.fragment_image_screen) {

	companion object {

		fun newInstance(
			imageUrl: String
		) = ImageFragment().apply {
			this.imageUrl = imageUrl
		}
	}

	private val viewModel:ImageViewModel by viewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val imageView: ImageView = requireActivity().findViewById(R.id.image_view)
		Glide.with(requireContext()).load(imageUrl).into(imageView)

		imageView.setOnClickListener { viewModel.navigateBack() }
	}
}