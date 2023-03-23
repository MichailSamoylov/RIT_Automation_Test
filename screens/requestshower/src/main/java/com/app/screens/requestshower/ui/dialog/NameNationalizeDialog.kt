package com.app.screens.requestshower.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.app.screens.requestshower.R
import com.app.screens.requestshower.databinding.FragmentDialogBinding

private const val NAME_KEY = "NAME_KEY"
private const val NATIONALIZE_KEY = "NATIONALIZE_KEY"

var NameNationalizeDialog.name
	get() = arguments?.getString(NAME_KEY) ?: throw IllegalArgumentException("Arguments can't be null")
	set(value) {
		arguments = arguments?.also { it.putString(NAME_KEY, value) } ?: bundleOf(NAME_KEY to value)
	}

var NameNationalizeDialog.nationalizes: String
	get() = arguments?.getString(NATIONALIZE_KEY) ?: throw IllegalArgumentException("Arguments can't be null")
	set(value) {
		arguments = arguments?.also { it.putString(NATIONALIZE_KEY, value) } ?: bundleOf(NATIONALIZE_KEY to value)
	}

class NameNationalizeDialog : DialogFragment(), View.OnClickListener {

	private lateinit var binding: FragmentDialogBinding

	companion object {

		fun newInstance(
			name: String,
			nationalizes: String
		) = NameNationalizeDialog().apply {
			this.name = name
			this.nationalizes = nationalizes
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val v: View = inflater.inflate(R.layout.fragment_dialog, null)
		binding = FragmentDialogBinding.bind(v)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		with(binding) {
			canselBtm.setOnClickListener(this@NameNationalizeDialog)
			nameText.text = name
			countryText.text = nationalizes
		}
	}

	override fun onClick(p0: View?) {
		dismiss()
	}
}