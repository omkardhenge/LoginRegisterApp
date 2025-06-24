package com.example.loginregisterapp.data.repository

import com.example.loginregisterapp.data.remote.AuthApi
import com.example.loginregisterapp.data.model.AuthRequest
import com.example.loginregisterapp.domain.repository.AuthRepository
import com.example.loginregisterapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun loginUser(request: AuthRequest): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.login(request)
            when (response.code()) {
                200 -> emit(Resource.Success("Login successful"))
                401 -> emit(Resource.Error("Please register or check your credentials"))
                else -> emit(Resource.Error("Unexpected error occurred (code: ${response.code()})"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }

    override suspend fun registerUser(request: AuthRequest): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.register(request)
            when (response.code()) {
                201 -> emit(Resource.Success("Registration successful"))
                409 -> emit(Resource.Error("Username already exists")) // 🔥 Add this
                else -> emit(Resource.Error("Registration failed (code: ${response.code()})"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }
}
