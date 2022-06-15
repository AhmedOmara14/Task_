package com.bosta.task.domain.use_case.get_all_users

import android.util.Log
import com.bosta.task.common.Resource
import com.bosta.task.data.remote.dto.all_users.toUsers
import com.bosta.task.domain.model.album.Album
import com.bosta.task.domain.model.all_users.AllUsers
import com.bosta.task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(val repository: TaskRepository) {
    operator fun invoke():Flow<Resource<List<AllUsers>>> = flow {
        try {
            emit(Resource.Loading<List<AllUsers>>())
            val users = repository.getAllUsers().map { it.toUsers() }
            emit(Resource.Success(users))
        }catch (e:HttpException){
            emit(Resource.Error<List<AllUsers>>(e.localizedMessage ?: "an Error Occurred"))
        }catch (e:IOException){
            emit(Resource.Error<List<AllUsers>>("No Internet Connection, Check your Internet"))
        }
    }
}