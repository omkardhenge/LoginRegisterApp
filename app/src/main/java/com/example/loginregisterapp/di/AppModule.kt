package com.example.loginregisterapp.di

import com.example.loginregisterapp.data.remote.AuthApi
import com.example.loginregisterapp.data.repository.AuthRepositoryImpl
import com.example.loginregisterapp.domain.repository.AuthRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.loginregisterapp.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository = AuthRepositoryImpl(api)
}
