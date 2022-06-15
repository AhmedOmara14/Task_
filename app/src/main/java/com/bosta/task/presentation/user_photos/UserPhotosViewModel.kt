package com.bosta.task.presentation.user_photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.task.common.Constants
import com.bosta.task.common.Resource
import com.bosta.task.domain.use_case.get_all_photos.GetAllPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserPhotosViewModel @Inject constructor(
    val getAllPhotosUseCase: GetAllPhotosUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(UserPhotosState())
    val state: State<UserPhotosState> = _state


    init {
        savedStateHandle.get<String>(Constants.ALBUM_ID)?.let { albumId ->
            getPhotosById(albumId)
        }
    }

    private fun getPhotosById(albumId: String) {

        getAllPhotosUseCase(albumId).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _state.value = UserPhotosState(userPhotos = response.data ?: emptyList())

                }
                is Resource.Error -> {
                    _state.value = UserPhotosState(error = response.message ?: "unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = UserPhotosState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}