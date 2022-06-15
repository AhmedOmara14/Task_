package com.bosta.task.presentation.user_profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.task.common.Constants
import com.bosta.task.common.Resource
import com.bosta.task.domain.use_case.get_all_albums.GetAllAlbumsUseCase
import com.bosta.task.domain.use_case.get_user_profile.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    val getUserProfileUseCase: GetUserProfileUseCase,
    val getAllAlbumsUseCase: GetAllAlbumsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(UserProfileState())
    val state: State<UserProfileState> = _state

    private val _stateAlbum = mutableStateOf(UserAlbumsState())
    val stateAlbum: State<UserAlbumsState> = _stateAlbum

    init {
        savedStateHandle.get<String>(Constants.USER_ID)?.let { userId ->
            getUserById(userId)
            getAlbum(userId)
        }
    }

    private fun getUserById(userId: String) {

        getUserProfileUseCase(userId).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _state.value = UserProfileState(userProfile = response.data ?: emptyList())

                }
                is Resource.Error -> {
                    _state.value = UserProfileState(error = response.message ?: "unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = UserProfileState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAlbum(userId: String) {
        getAllAlbumsUseCase(userId).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _stateAlbum.value = UserAlbumsState(album = response.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateAlbum.value =
                        UserAlbumsState(error = response.message ?: "unexpected error")
                }
                is Resource.Loading -> {
                    _stateAlbum.value = UserAlbumsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}