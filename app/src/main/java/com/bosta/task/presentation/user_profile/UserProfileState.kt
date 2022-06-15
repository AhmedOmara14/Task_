package com.bosta.task.presentation.user_profile
import com.bosta.task.domain.model.album.Album
import com.bosta.task.domain.model.user_profile.UserProfileItem

data class UserProfileState(
    val isLoading: Boolean = false,
    val userProfile: List<UserProfileItem> = emptyList(),
    val error: String = "",
)