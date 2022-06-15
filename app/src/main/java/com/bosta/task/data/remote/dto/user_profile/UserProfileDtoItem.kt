package com.bosta.task.data.remote.dto.user_profile

import com.bosta.task.domain.model.user_profile.Address
import com.bosta.task.domain.model.user_profile.Company
import com.bosta.task.domain.model.user_profile.UserProfileItem

data class UserProfileDtoItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun UserProfileDtoItem.toUser(): UserProfileItem {
    return UserProfileItem(address,company,email, id, name, phone, username, website)
}