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
