package com.example.api.services

import com.example.api.data.models.profile.ResponseProfile
import com.example.api.data.models.upload.ResponseUpload
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Uploadservice {
   
    @Multipart
    @POST ("files/upload")
    suspend fun upload( @Part file : MultipartBody.Part): Response<ResponseUpload>


}