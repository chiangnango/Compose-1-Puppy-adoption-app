package com.example.androiddevchallenge

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.api.DogService
import com.example.androiddevchallenge.modal.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    dogService: DogService
) : ViewModel(), LifecycleObserver {

    var breeds: List<Breed> by mutableStateOf(listOf())
        private set

    init {
        viewModelScope.launch {
            runCatching {
                dogService.getAllBreeds()
            }.onFailure {
                Log.e(TAG, "getAllBreeds failed", it)
            }.onSuccess {
                Log.e(TAG, "getAllBreeds success $it")
                breeds = it.filter { breed ->
                    breed.name.isNotBlank()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
