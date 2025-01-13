package com.carlossierrasequera.dogapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carlossierrasequera.dogapp.ui.theme.DogAppTheme


@Composable
fun MainScreen(
    dogViewModel: DogViewModel = viewModel(),
    onShare: (String) -> Unit
) {
    val dogImageUrl = dogViewModel.dogImageUrl.value
    val isLoading = dogImageUrl.isEmpty()

    // Cuando la pantalla se inicializa, obtiene una nueva imagen
    LaunchedEffect(Unit) {
        dogViewModel.getNewDogImage()
    }

    DogAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Distribuye los elementos uniformemente
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // Espaciado superior para margen inicial

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp) // Tamaño del indicador de progreso
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                Image(
                    painter = rememberImagePainter(dogImageUrl),
                    contentDescription = "Imagen de perro",
                    modifier = Modifier
                        .size(200.dp) // Tamaño de la imagen
                        .clip(CircleShape) // Forma circular
                        .align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { dogViewModel.getNewDogImage() }) {
                    Text("Obtener otro perro")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { if (dogImageUrl.isNotEmpty()) onShare(dogImageUrl) }) {
                    Text("Compartir")
                }
            }
        }
    }
}