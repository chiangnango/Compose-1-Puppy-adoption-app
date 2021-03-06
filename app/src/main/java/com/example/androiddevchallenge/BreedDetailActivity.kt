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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MainActivity.Companion.KEY_BREED
import com.example.androiddevchallenge.modal.Breed
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class BreedDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                BreedDetailScreen(requireNotNull(intent.getParcelableExtra(KEY_BREED)))
            }
        }
    }
}

@Composable
fun BreedDetailScreen(breed: Breed) {
    Surface(color = MaterialTheme.colors.background) {
        BreedDetail(breed)
    }
}

@Composable
fun BreedDetail(breed: Breed) {
    Column(Modifier.fillMaxSize()) {
        GlideImage(
            data = breed.image.url,
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "Breed photo"
        )
        Column(Modifier.padding(16.dp)) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.h6
            )
            breed.bredFor?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}
