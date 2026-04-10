package com.example.api.services

import com.example.api.data.models.profile.ResponseProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Userservice {
   

    @GET("auth/profile")
    suspend fun profile (): Response<ResponseProfile>


}