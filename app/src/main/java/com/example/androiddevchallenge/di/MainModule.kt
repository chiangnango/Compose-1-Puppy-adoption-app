package com.example.androiddevchallenge.di

import com.example.androiddevchallenge.api.DogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class MainModule {

    @Provides
    fun providesDogService(retrofit: Retrofit): DogService {
        return retrofit.create(DogService::class.java)
    }
}
