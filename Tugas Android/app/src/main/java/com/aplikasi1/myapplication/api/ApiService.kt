package com.aplikasi1.myapplication.api

import com.aplikasi1.myapplication.model.UserDetailModel
import com.aplikasi1.myapplication.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun users(): List<UserModel>

    @GET("users/{username}")
    suspend fun detailUser(@Path("username") username: String): UserDetailModel
}