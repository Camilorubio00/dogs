package com.example.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.core.CoroutinesDispatchers
import com.example.dogs.extensions.Result
import com.example.dogs.domain.FetchDogsUseCase
import com.example.dogs.domain.model.Dog
import com.example.dogs.ui.model.DogUiModelState
import com.example.dogs.ui.model.toDogUiList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchDogsUseCase: FetchDogsUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _dogsUiModelState = MutableStateFlow<DogUiModelState?>(null)

    val dogsUiModelState: StateFlow<DogUiModelState?>
        get() = _dogsUiModelState

    fun fetchDogs() {
        emitDogsUiState(DogUiModelState.Loading)
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = fetchDogsUseCase()
            withContext(coroutinesDispatchers.main) {
                when (result) {
                    is Result.Success -> emitDogsUiStateSuccess(result.data)
                    is Result.Error -> Unit
                }
            }
        }
    }

    private fun emitDogsUiStateSuccess(dogs: List<Dog>) = emitDogsUiState(DogUiModelState.Success(dogs.toDogUiList()))

    private fun emitDogsUiState(dogUiModelState: DogUiModelState) {
        _dogsUiModelState.value = dogUiModelState
    }
}
