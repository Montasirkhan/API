package com.example.api.services

import com.example.api.data.models.Registration.RequestRagistration
import com.example.api.data.models.Registration.ResponseRegistration
import com.example.api.data.models.login.RequestLogin
import com.example.api.data.models.login.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface Authservice {


    @POST("auth/login")
   suspend fun login(@Body requestLogin: RequestLogin): Response<ResponseLogin>


    @POST("users")
    suspend fun registration(@Body request: RequestRagistration): Response<ResponseRegistration>


}