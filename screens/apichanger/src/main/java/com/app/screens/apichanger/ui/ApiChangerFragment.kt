package com.app.screens.apichanger.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.app.components.fragment.FragmentResultKey
import com.app.screens.apichanger.R
import com.app.screens.apichanger.databinding.FragmentApiChangerBinding
import com.app.screens.apichanger.presentation.ApiChangerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ApiChangerFragment : Fragment(R.layout.fragment_api_changer), OnItemSelectedListener {

	companion object {

		fun newInstance() = ApiChangerFragment()
	}

	private val viewModel: ApiChangerViewModel by viewModel {
		parametersOf(resources.getStringArray(R.array.api_name_array).toList())
	}
	private lateinit var binding: FragmentApiChangerBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentApiChangerBinding.inflate(inflater, container, false)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewInitial()
		setListeners()
	}

	private fun viewInitial() {
		val adapter: ArrayAdapter<String> =
			ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_item, resources.getStringArray(R.array.api_name_array))
		binding.apiPicker.adapter = adapter
	}

	private fun setListeners() {
		with(binding) {
			saveAndReturnBtm.setOnClickListener {
				setFragmentResult(FragmentResultKey.key, bundleOf(FragmentResultKey.apiKey to viewModel.apiName.value))
				viewModel.navigateBack()
			}
			apiPicker.onItemSelectedListener = this@ApiChangerFragment
		}
	}

	override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p: Int, p3: Long) {
		viewModel.setApiName(binding.apiPicker.getItemAtPosition(p).toString())
	}

	override fun onNothingSelected(p0: AdapterView<*>?) {
		viewModel.setApiName(binding.apiPicker.getItemAtPosition(0).toString())
	}
}