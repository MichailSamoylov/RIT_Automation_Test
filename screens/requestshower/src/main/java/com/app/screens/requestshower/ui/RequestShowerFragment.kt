package com.app.screens.requestshower.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.app.components.apiname.ApiNames
import com.app.components.fragment.FragmentResultKey
import com.app.screens.requestshower.R
import com.app.screens.requestshower.databinding.FragmentRequestShowerBinding
import com.app.screens.requestshower.presentation.ApiEntityWrap
import com.app.screens.requestshower.presentation.RequestShowerState
import com.app.screens.requestshower.presentation.RequestShowerViewModel
import com.app.screens.requestshower.ui.dialog.NameNationalizeDialog
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class RequestShowerFragment : Fragment() {

	companion object {

		const val SHARED_PREFERENCE_NAME = "SHARED_PREFERENCE_NAME"
		const val LAST_API_KEY = "LAST_API_KEY"
		fun newInstance() = RequestShowerFragment()
	}

	private lateinit var binding: FragmentRequestShowerBinding
	private val viewModel: RequestShowerViewModel by viewModel()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		super.onCreateView(inflater, container, savedInstanceState)
		checkAppSetting()
		binding = FragmentRequestShowerBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setListeners()
		viewModel.state.observe(viewLifecycleOwner, ::handleState)
	}

	private fun setListeners() {
		setFragmentResultListener(FragmentResultKey.key) { _, bundle ->
			viewModel.setApi(bundle.getSerializable(FragmentResultKey.apiKey) as? ApiNames ?: ApiNames.DOGS)
			saveSelectedApi(bundle.getSerializable(FragmentResultKey.apiKey) as? ApiNames ?: ApiNames.DOGS)
		}
		with(binding) {
			sendRequestBtm.setOnClickListener { viewModel.sendApiRequest() }
			chooseApiBtm.setOnClickListener { viewModel.navigateToApis() }
			nameEditText.doOnTextChanged { text, _, _, _ -> viewModel.setName(text.toString()) }
			customUrlEditText.doOnTextChanged { text, _, _, _ -> viewModel.setCustomUrl(text.toString()) }
			dogsImageResult.setOnClickListener { viewModel.navigateToImageScreen() }
		}
	}

	private fun checkAppSetting() {
		val sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
		when (sharedPreferences.getInt(LAST_API_KEY, 0)) {
			ApiNames.DOGS.code        -> {
				viewModel.setApi(ApiNames.DOGS)
			}

			ApiNames.NATIONALIZE.code -> {
				viewModel.setApi(ApiNames.NATIONALIZE)
			}

			ApiNames.CUSTOM.code      -> {
				viewModel.setApi(ApiNames.CUSTOM)
			}
		}
	}

	private fun handleState(state: RequestShowerState) {
		when (state) {
			is RequestShowerState.Content -> renderContent(state)
			is RequestShowerState.Error   -> renderError(state)
			RequestShowerState.Initial    -> Unit
			RequestShowerState.Loading    -> renderLoading()
		}
	}

	private fun renderLoading() {
		with(binding) {
			content.visibility = View.GONE
			progressBar.visibility = View.VISIBLE
		}
	}

	private fun renderError(state: RequestShowerState.Error) {
		Toast.makeText(requireContext(), state.throwableMessage, Toast.LENGTH_LONG).show()
	}

	private fun renderContent(state: RequestShowerState.Content) {
		with(binding) {
			content.visibility = View.VISIBLE
			progressBar.visibility = View.GONE

			hideAndClearExcess()

			when (state.apiName) {
				ApiNames.DOGS        -> {
					customUrlEditText.text?.clear()
					nameEditText.text?.clear()
					dogsImageResult.visibility = View.VISIBLE
				}

				ApiNames.NATIONALIZE -> {
					customUrlEditText.text?.clear()
					nameEditTextLayout.visibility = View.VISIBLE
				}

				ApiNames.CUSTOM      -> {
					nameEditText.text?.clear()
					customUrlEditTextLayout.visibility = View.VISIBLE
				}
			}

			apiName.text = when (state.apiName) {
				ApiNames.CUSTOM      -> resources.getString(R.string.custom_api_text)
				ApiNames.DOGS        -> resources.getString(R.string.dogs_api_text)
				ApiNames.NATIONALIZE -> resources.getString(R.string.nationalize_api_text)
			}

			when (state.apiEntityWrap) {
				is ApiEntityWrap.CustomApiWrap       -> renderCustomApi(state.apiEntityWrap)

				is ApiEntityWrap.DogsApiWrap         -> renderDogsApi(state.apiEntityWrap)

				is ApiEntityWrap.NationalizeApiWrap  -> renderNationalizeApi(state.apiEntityWrap)

				is ApiEntityWrap.NationalizesApiWrap -> renderNationalizesApi(state.apiEntityWrap)

				null                                 -> Unit
			}
		}
	}

	private fun renderCustomApi(wrap: ApiEntityWrap.CustomApiWrap) {
		with(binding) {
			if (wrap.url.isNotEmpty()) {
				webView.visibility = View.VISIBLE
				webView.loadUrl(wrap.url)
			}
		}
	}

	private fun renderDogsApi(wrap: ApiEntityWrap.DogsApiWrap) {
		with(binding) {
			Glide
				.with(requireContext())
				.load(wrap.dogsEntity.dogsImage)
				.into(dogsImageResult)
		}
	}

	private fun renderNationalizeApi(wrap: ApiEntityWrap.NationalizeApiWrap) {
		if (wrap.nationalizeEntity.country.isNotEmpty()) {
			val nationalizes = buildString {
				wrap.nationalizeEntity.country.forEach { item -> append(item.toString()) }
			}
			val dialog = NameNationalizeDialog.newInstance(wrap.name, nationalizes)
			dialog.show(childFragmentManager, "Nationalize")
		}
	}

	private fun renderNationalizesApi(wrap: ApiEntityWrap.NationalizesApiWrap) {
		val countries = StringBuilder()
		wrap.nationalizesEntity.forEach {
			countries.append("${it.name}. ${it.country}\n\n")
		}
		if (wrap.nationalizesEntity.isNotEmpty()) {
			val dialog = NameNationalizeDialog.newInstance("", countries.toString())
			dialog.show(childFragmentManager, "Nationalize")
		}
	}

	private fun hideAndClearExcess() {
		with(binding) {
			webView.visibility = View.GONE
			dogsImageResult.visibility = View.GONE
			nameEditTextLayout.visibility = View.GONE
			customUrlEditTextLayout.visibility = View.GONE
		}
	}

	private fun saveSelectedApi(apiNames: ApiNames) {
		val sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
		sharedPreferences.edit().apply {
			putInt(LAST_API_KEY, apiNames.code)
		}.apply()
	}
}