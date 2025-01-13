package com.carlossierrasequera.dogapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlossierrasequera.dogapp.data.ApiClient
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    //almacena la url de la imagen del perro
    var dogImageUrl = mutableStateOf("")
        private set

    init {
        //obtiene la primera imagen cuando se inicializa el ViewModel
        getNewDogImage()
    }

    //función que obtiene una nueva imagen del perro
    fun getNewDogImage() {
        //llamada a la api para obtener la URL de la imagen
        viewModelScope.launch {
            try {
                val dogResponse = ApiClient.getDogApiService().getRandomDogImage()
                dogImageUrl.value = dogResponse.message //se asigna de la imagen recibida
            } catch (e: Exception) {
                dogImageUrl.value = "Error al cargar la imagen"
            }
        }
    }
}