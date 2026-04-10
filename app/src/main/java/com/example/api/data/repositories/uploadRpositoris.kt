package com.example.api.data.repositories

import com.example.api.data.models.profile.ResponseProfile
import com.example.api.data.models.upload.ResponseUpload
import com.example.api.services.Uploadservice
import com.example.api.services.Userservice
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class uploadRpositoris @Inject constructor(
    private val service: Uploadservice
) {

    suspend fun upload(file : MultipartBody.Part): Response<ResponseUpload> {
        return service.upload(file)
    }
}