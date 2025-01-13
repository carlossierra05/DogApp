package com.carlossierrasequera.dogapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carlossierrasequera.dogapp.ui.DogViewModel
import com.carlossierrasequera.dogapp.ui.MainScreen
import com.carlossierrasequera.dogapp.ui.theme.DogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogAppTheme {
                val dogViewModel: DogViewModel = viewModel()
                MainScreen(dogViewModel, ::shareImage)
            }
        }
    }

    private fun shareImage(imageUrl: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "¡Mira este perro! $imageUrl")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Compartir imagen"))
    }



}