package com.example.loginregisterapp.data.remote

import com.example.loginregisterapp.data.model.AuthRequest
import com.example.loginregisterapp.data.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



interface AuthApi {
    @POST("/register")
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>

    @POST("/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>
}
