package com.bosta.task.data.remote.dto.album

import com.bosta.task.domain.model.album.Album

data class AlbumDto(
    val id: Int,
    val title: String,
    val userId: Int
)

fun AlbumDto.toAlbum(): Album {
    return Album(id, title, userId)
}