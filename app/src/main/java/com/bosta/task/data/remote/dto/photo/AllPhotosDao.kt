package com.bosta.task.data.remote.dto.photo

import com.bosta.task.domain.model.photos.AllPhotosItem

data class AllPhotosDao(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
fun AllPhotosDao.toPhoto():AllPhotosItem{
    return AllPhotosItem(albumId, id, thumbnailUrl, title, url)
}