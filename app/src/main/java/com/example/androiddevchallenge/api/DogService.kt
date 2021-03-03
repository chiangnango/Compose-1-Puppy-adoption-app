package com.example.androiddevchallenge.api

import com.example.androiddevchallenge.modal.Breed
import retrofit2.http.GET

interface DogService {

    @GET("breeds")
    suspend fun getAllBreeds(): List<Breed>
}
