package com.example.api.data.repositories

import com.example.api.data.models.profile.ResponseProfile
import com.example.api.services.Userservice
import retrofit2.Response
import javax.inject.Inject

class userRpositoris @Inject constructor(
    private val service: Userservice
) {

    suspend fun profile(): Response<ResponseProfile> {
        return service.profile()
    }
}