package com.bosta.task.data.repository

import com.bosta.task.data.remote.Api
import com.bosta.task.data.remote.dto.album.AlbumDto
import com.bosta.task.data.remote.dto.all_users.AllUsersDto
import com.bosta.task.data.remote.dto.photo.AllPhotosDao
import com.bosta.task.data.remote.dto.user_profile.UserProfileDtoItem
import com.bosta.task.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(val api: Api) :TaskRepository {
    override suspend fun getAllUsers(): List<AllUsersDto> {
       return api.getAllUsers()
    }
    override suspend fun getUserById(userId: String): List<UserProfileDtoItem> {
        return api.getUserById(userId)
    }
    override suspend fun getAllAlbums(userId:String): List<AlbumDto> {
        return api.getAllAlbums(userId)
    }
    override suspend fun getAllPhotos(albumId:String): List<AllPhotosDao> {
        return api.getAllPhotos(albumId)
    }
}