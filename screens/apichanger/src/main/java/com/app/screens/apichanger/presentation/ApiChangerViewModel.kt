package com.app.screens.apichanger.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.components.apiname.ApiNames

class ApiChangerViewModel(
	private val router: ApiChangerRouter,
	private val names: List<String>
) : ViewModel() {

	private val _apiName = MutableLiveData<ApiNames>(ApiNames.DOGS)
	val apiName: LiveData<ApiNames> = _apiName

	fun setApiName(name: String) {
		when (names.indexOf(name)) {
			ApiNames.DOGS.code -> {
				_apiName.value = ApiNames.DOGS
			}

			ApiNames.NATIONALIZE.code -> {
				_apiName.value = ApiNames.NATIONALIZE
			}

			ApiNames.CUSTOM.code -> {
				_apiName.value = ApiNames.CUSTOM
			}
		}
	}

	fun navigateBack() {
		router.navigateBack()
	}
}