package com.bosta.task.presentation.users_list

import com.bosta.task.domain.model.all_users.AllUsers

data class ListUsersState (
    val isLoading:Boolean =false,
    val users:List<AllUsers> = emptyList(),
    val error:String =""
)