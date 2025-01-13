package com.carlossierrasequera.dogapp.data


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DogApiService {
    //coge una imagen aleatoria de un perro
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogResponse
}

