package com.example.api.data.repositories

import com.example.api.data.models.Registration.RequestRagistration
import com.example.api.data.models.Registration.ResponseRegistration
import com.example.api.data.models.login.RequestLogin
import com.example.api.data.models.login.ResponseLogin
import com.example.api.services.Authservice
import retrofit2.Response
import javax.inject.Inject
class AuthRpositoris@Inject constructor(private val service: Authservice) {



    suspend fun login(requestLogin: RequestLogin): Response<ResponseLogin> {
        return service.login(requestLogin)
    }



    suspend fun registration(request: RequestRagistration) : Response<ResponseRegistration> {
        return service.registration(request)
    }
}