package com.bosta.task.presentation.user_profile
import com.bosta.task.domain.model.album.Album
import com.bosta.task.domain.model.user_profile.UserProfileItem

data class UserAlbumsState(
    val isLoading: Boolean = false,
    val album: List<Album> = emptyList(),
    val error: String = "",
)