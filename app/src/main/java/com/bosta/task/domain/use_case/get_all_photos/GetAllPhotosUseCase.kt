package com.bosta.task.domain.use_case.get_all_photos

import com.bosta.task.common.Resource
import com.bosta.task.data.remote.dto.album.toAlbum
import com.bosta.task.data.remote.dto.photo.toPhoto
import com.bosta.task.domain.model.album.Album
import com.bosta.task.domain.model.photos.AllPhotosItem
import com.bosta.task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPhotosUseCase @Inject constructor(val repository: TaskRepository) {
    operator fun invoke(albumId :String): Flow<Resource<List<AllPhotosItem>>> =
        flow {
            try {
                emit(Resource.Loading<List<AllPhotosItem>>())
                val album = repository.getAllPhotos(albumId).map { it.toPhoto() }
                emit(Resource.Success(album))
            } catch (e: HttpException) {
                emit(Resource.Error<List<AllPhotosItem>>(e.localizedMessage ?: "an Error Occurred"))
            } catch (e: IOException) {
                emit(Resource.Error<List<AllPhotosItem>>("No Internet Connection, Check your Internet"))
            }
        }
}