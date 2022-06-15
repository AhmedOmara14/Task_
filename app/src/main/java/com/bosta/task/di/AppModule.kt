package com.bosta.task.di
import com.bosta.task.common.Constants.BASE_URL
import com.bosta.task.data.remote.Api
import com.bosta.task.data.repository.TaskRepositoryImpl
import com.bosta.task.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit() :Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: Api):TaskRepository{
        return TaskRepositoryImpl(api)
    }


}