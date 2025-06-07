package com.example.dogs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.core.CoroutinesDispatchers
import com.example.dogs.domain.FetchDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchDogsUseCase: FetchDogsUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    fun fetchDogs() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = fetchDogsUseCase()
            Log.d("MainActivityViewModel", "fetchDogs: $result")
        }
    }
}
