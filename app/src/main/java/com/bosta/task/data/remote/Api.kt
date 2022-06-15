package com.bosta.task.data.remote

import com.bosta.task.data.remote.dto.album.AlbumDto
import com.bosta.task.data.remote.dto.all_users.AllUsersDto
import com.bosta.task.data.remote.dto.photo.AllPhotosDao
import com.bosta.task.data.remote.dto.user_profile.UserProfileDtoItem
import com.bosta.task.domain.model.user_profile.UserProfileItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
  @GET("users")
  suspend fun getAllUsers() :List<AllUsersDto>

  @GET("users")
  suspend fun getUserById(@Query("id") userId:String) : List<UserProfileDtoItem>

  @GET("albums")
  suspend fun getAllAlbums(@Query("userId") userId:String) : List<AlbumDto>

  @GET("photos")
  suspend fun getAllPhotos(@Query("albumId") albumId:String) : List<AllPhotosDao>

}