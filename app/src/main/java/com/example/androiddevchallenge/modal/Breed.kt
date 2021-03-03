package com.example.androiddevchallenge.modal

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Breed(
    val id: String,
    val name: String,
    @Json(name = "bred_for")
    val bredFor: String? = "",
    val image: Image
) : Parcelable
