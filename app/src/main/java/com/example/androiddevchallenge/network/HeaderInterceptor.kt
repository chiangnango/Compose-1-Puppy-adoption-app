package com.example.androiddevchallenge.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {

    private val defaultHeaders: Map<String, String> = mapOf(
//        "x-api-key" to ""
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().apply {
            defaultHeaders.forEach {
                addHeader(it.key, it.value)
            }
        }.build()
        return chain.proceed(newRequest)
    }
}
