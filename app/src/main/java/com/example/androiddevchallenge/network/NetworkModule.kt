/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class IODispatcher

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Reusable
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(headerInterceptor)
        readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }.build()

    @Reusable
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Reusable
    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    companion object {
        private const val BASE_URL = "https://api.thedogapi.com/v1/"
        private const val NETWORK_TIMEOUT_SECONDS = 30L
    }
}
