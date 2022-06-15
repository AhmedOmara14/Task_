package com.bosta.task.presentation.users_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.task.common.Resource
import com.bosta.task.domain.use_case.get_all_users.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListUsersViewModel @Inject constructor(val getAllUsersUseCase: GetAllUsersUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(ListUsersState())
    val state: State<ListUsersState> = _state

    init {
        getAllUsers()
    }

    fun getAllUsers() {
        getAllUsersUseCase().onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _state.value = ListUsersState(users = response.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ListUsersState(error = response.message ?: "unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = ListUsersState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}