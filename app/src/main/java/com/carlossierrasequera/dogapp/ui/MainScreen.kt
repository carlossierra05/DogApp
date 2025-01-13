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
    dogViewModel: DogViewModel = viewModel(), //obtiene el viewmodel para manejar el estado
    onShare: (String) -> Unit //compartir la url de la imagen
) {
    val dogImageUrl = dogViewModel.dogImageUrl.value
    val isLoading = dogImageUrl.isEmpty()

    //carga imagen cuando la pantalla se inicializa
    LaunchedEffect(Unit) {
        dogViewModel.getNewDogImage()
    }

    DogAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                Image(
                    painter = rememberImagePainter(dogImageUrl),
                    contentDescription = "Imagen de perro",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                )
            }

            //botones
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Botón de nueva imagen
                Button(onClick = { dogViewModel.getNewDogImage() }) {
                    Text("Obtener otro perro")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botón de compartir
                Button(onClick = { if (dogImageUrl.isNotEmpty()) onShare(dogImageUrl) }) {
                    Text("Compartir")
                }
            }
        }
    }
}
