package com.aplikasi1.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun users(): List<UserModel>

    @GET("users/{username}")
    suspend fun detailUser(@Path("username") username: String): UserDetailModel
}