package com.carlossierrasequera.dogapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlossierrasequera.dogapp.data.ApiClient
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    // Estado que almacena la URL de la imagen del perro
    var dogImageUrl = mutableStateOf("")
        private set

    init {
        // Obtener la primera imagen cuando se inicializa el ViewModel
        getNewDogImage()
    }

    // Función para obtener una nueva imagen del perro
    fun getNewDogImage() {
        // Llamamos a la API para obtener la URL de la imagen de un perro
        viewModelScope.launch {
            try {
                val dogResponse = ApiClient.getDogApiService().getRandomDogImage()
                dogImageUrl.value = dogResponse.message // Asignamos la URL de la imagen recibida
            } catch (e: Exception) {
                // Manejo de errores, si ocurre algún fallo en la solicitud
                dogImageUrl.value = ""
            }
        }
    }
}