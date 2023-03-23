package com.app.screens.requestshower.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.api.dogs.domain.usecase.GetRandomDogsUseCase
import com.app.api.nationalize.domain.entity.NationalizeEntity
import com.app.api.nationalize.domain.usecase.GetNamesNationalizesUseCase
import com.app.api.nationalize.domain.usecase.GetNationalizeByNameUseCase
import com.app.components.apiname.ApiNames
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class RequestShowerViewModel(
	private val router: RequestShowerRouter,
	private val dogsUseCase: GetRandomDogsUseCase,
	private val nameNationalizeUseCase: GetNationalizeByNameUseCase,
	private val namesNationalizesUseCase: GetNamesNationalizesUseCase,
) : ViewModel() {

	private val _state = MutableLiveData<RequestShowerState>(RequestShowerState.Initial)
	val state: LiveData<RequestShowerState> = _state

	init {
		_state.value = RequestShowerState.Content(ApiNames.DOGS, null)
	}

	fun setApi(name: ApiNames) {
		val tempState = _state.value as? RequestShowerState.Content ?: return
		_state.value = tempState.copy(apiName = name)
	}

	fun setCustomUrl(url: String) {
		if (url.isNotEmpty()) {
			val tempState = _state.value as? RequestShowerState.Content ?: return
			_state.value = tempState.copy(apiEntityWrap = ApiEntityWrap.CustomApiWrap(url, ""))
		}
	}

	fun setName(name: String) {
		if (name.isNotEmpty()) {
			val tempState = _state.value as? RequestShowerState.Content ?: return
			_state.value = tempState.copy(apiEntityWrap = ApiEntityWrap.NationalizeApiWrap(name, NationalizeEntity(listOf(), name)))
		}
	}

	private fun splitNames(names: String): List<String> {
		val result = mutableListOf<String>()
		val stringBuilder = StringBuilder()
		names.forEach {
			if (it == ',') {
				result.add(stringBuilder.toString())
				stringBuilder.clear()
			}else{
				stringBuilder.append(it)
			}
		}
		result.add(stringBuilder.toString())
		return result
	}

	fun sendApiRequest() {
		val tempState = _state.value as? RequestShowerState.Content ?: return
		_state.value = RequestShowerState.Loading
		viewModelScope.launch(
			CoroutineExceptionHandler { coroutineContext, throwable ->
				_state.value = RequestShowerState.Error(throwable.message.toString())
				_state.value = tempState
			}
		) {
			when (tempState.apiName) {
				ApiNames.DOGS -> {
					_state.value = tempState.copy(apiEntityWrap = ApiEntityWrap.DogsApiWrap(dogsUseCase()))
				}

				ApiNames.NATIONALIZE -> {
					val name = (tempState.apiEntityWrap as ApiEntityWrap.NationalizeApiWrap).name

					if(name.contains(",")){
						_state.value = tempState.copy(apiEntityWrap = ApiEntityWrap.NationalizesApiWrap(name, namesNationalizesUseCase(splitNames(name))))
					}else{
						_state.value = tempState.copy(apiEntityWrap = ApiEntityWrap.NationalizeApiWrap(name, nameNationalizeUseCase(name)))
					}
				}

				ApiNames.CUSTOM -> {
					_state.value = tempState.copy()
				}
			}
		}
	}

	fun navigateToApis() {
		router.navigateToApis()
	}

	fun navigateToImageScreen() {
		val tempState = _state.value as? RequestShowerState.Content ?: return
		val dogsWrap = tempState.apiEntityWrap as? ApiEntityWrap.DogsApiWrap ?: return
		router.navigateToImageScreen(dogsWrap.dogsEntity.dogsImage)
	}
}