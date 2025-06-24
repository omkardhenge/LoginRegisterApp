package com.example.loginregisterapp.domain.repository
import com.example.loginregisterapp.data.model.AuthRequest
import com.example.loginregisterapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(request: AuthRequest): Flow<Resource<String>>
    suspend fun registerUser(request: AuthRequest): Flow<Resource<String>>
}
