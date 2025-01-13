package com.carlossierrasequera.dogapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //url base de la api
    private const val BASE_URL = "https://dog.ceo/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    //metodo para obtener el servicio de la api
    fun getDogApiService(): DogApiService {
        //se crea y se implementa la interfaz con retrofit
        return retrofit.create(DogApiService::class.java)
    }
}
