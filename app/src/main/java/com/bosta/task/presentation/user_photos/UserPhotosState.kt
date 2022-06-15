package com.bosta.task.presentation.user_photos
import com.bosta.task.domain.model.photos.AllPhotosItem

data class UserPhotosState(
    val isLoading: Boolean = false,
    val userPhotos: List<AllPhotosItem> = emptyList(),
    val error: String = "",
)