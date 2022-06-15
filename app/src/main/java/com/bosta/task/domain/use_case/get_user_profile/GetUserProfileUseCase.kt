package com.bosta.task.domain.use_case.get_user_profile
import com.bosta.task.common.Resource
import com.bosta.task.data.remote.dto.user_profile.toUser
import com.bosta.task.domain.model.all_users.AllUsers
import com.bosta.task.domain.model.user_profile.UserProfileItem
import com.bosta.task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(val repository: TaskRepository) {
    operator fun invoke(userId: String): Flow<Resource<List<UserProfileItem>>>
    = flow {
        try {
            emit(Resource.Loading<List<UserProfileItem>>())
            val users = repository.getUserById(userId).map { it.toUser() }
            emit(Resource.Success(users))
        }catch (e:HttpException){
            emit(Resource.Error<List<UserProfileItem>>(e.localizedMessage ?: "an Error Occurred"))
        }catch (e:IOException){
            emit(Resource.Error<List<UserProfileItem>>("No Internet Connection, Check your Internet"))
        }
    }
}