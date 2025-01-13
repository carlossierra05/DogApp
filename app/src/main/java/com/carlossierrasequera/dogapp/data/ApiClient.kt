package com.carlossierrasequera.dogapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // URL base de la API de perros
    private const val BASE_URL = "https://dog.ceo/api/"

    // Creamos la instancia de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    // Devuelve el servicio para obtener las imágenes de perros
    fun getDogApiService(): DogApiService {
        return retrofit.create(DogApiService::class.java)
    }
}
