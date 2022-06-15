package com.bosta.task.domain.use_case.get_all_albums

import com.bosta.task.common.Resource
import com.bosta.task.data.remote.dto.album.toAlbum
import com.bosta.task.domain.model.album.Album
import com.bosta.task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllAlbumsUseCase @Inject constructor(val repository: TaskRepository) {
    operator fun invoke(userId :String): Flow<Resource<List<Album>>> =
        flow {
            try {
                emit(Resource.Loading<List<Album>>())
                val album = repository.getAllAlbums(userId).map { it.toAlbum() }
                emit(Resource.Success(album))
            } catch (e: HttpException) {
                emit(Resource.Error<List<Album>>(e.localizedMessage ?: "an Error Occurred"))
            } catch (e: IOException) {
                emit(Resource.Error<List<Album>>("No Internet Connection, Check your Internet"))
            }
        }
}