package com.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val prefManager: PrefManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // Get the token
        val token = prefManager.getPref(Keys.ACCESS_TOKEN)
        
        // If there's no token, just proceed with the original request
        if (token.isEmpty() || token == "null") {
            return chain.proceed(originalRequest)
        }

        // Build the new request with the header
        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
