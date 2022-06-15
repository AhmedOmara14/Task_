package com.bosta.task.domain.repository

import com.bosta.task.data.remote.dto.album.AlbumDto
import com.bosta.task.data.remote.dto.all_users.AllUsersDto
import com.bosta.task.data.remote.dto.photo.AllPhotosDao
import com.bosta.task.data.remote.dto.user_profile.UserProfileDtoItem

interface TaskRepository {
    suspend fun getAllUsers(): List<AllUsersDto>
    suspend fun getUserById(userId: String): List<UserProfileDtoItem>
    suspend fun getAllAlbums(userId: String): List<AlbumDto>
    suspend fun getAllPhotos(albumId: String): List<AllPhotosDao>
}