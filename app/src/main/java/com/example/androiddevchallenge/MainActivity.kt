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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.modal.Breed
import com.example.androiddevchallenge.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.glide.GlideImage

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                BreedListScreen(
                    onClick = {
                        startActivity(
                            Intent(this, BreedDetailActivity::class.java).apply {
                                putExtra(KEY_BREED, it)
                            }
                        )
                    }
                )
            }
        }
    }

    companion object {
        const val KEY_BREED = "breed"
    }
}

// Start building your app here!
@Composable
fun BreedListScreen(
    viewModel: MainViewModel = viewModel(),
    onClick: (Breed) -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        BreedList(viewModel.breeds, onClick)
    }
}

@Composable
fun BreedList(
    breeds: List<Breed>,
    onClick: (Breed) -> Unit
) {
    LazyColumn(
        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(1.dp)
    ) {
        this.items(breeds) { breed ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { onClick(breed) }
            ) {
                GlideImage(
                    data = breed.image.url,
                    modifier = Modifier.width(100.dp),
                    contentDescription = "Breed thumbnail"
                )
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = breed.name)
                    breed.bredFor?.let {
                        Text(
                            text = it,
                            color = Color.Gray
                        )
                    }
                }
            }
            Divider()
        }
    }
}
