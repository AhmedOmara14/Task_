package com.bosta.task.data.remote.dto.all_users

import com.bosta.task.domain.model.all_users.AllUsers

data class AllUsersDto(
    val id: Int,
    val name: String
)

fun AllUsersDto.toUsers(): AllUsers {
    return AllUsers(id, name)
}