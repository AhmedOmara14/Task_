package com.bosta.task.domain.model.photos

data class AllPhotosItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)