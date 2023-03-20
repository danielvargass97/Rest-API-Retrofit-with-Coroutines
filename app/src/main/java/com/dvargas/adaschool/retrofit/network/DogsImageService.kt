package com.dvargas.adaschool.retrofit.network

import com.dvargas.adaschool.retrofit.network.dto.DogsListDto
import com.dvargas.adaschool.retrofit.network.dto.RandomDogImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsImageService {

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<RandomDogImageDto>

    @GET("breeds/list/all")
    suspend fun getDogBreedsList(): Response<DogsListDto>

    @GET("breed/{dogBreed}/images/random")
    suspend fun getRandomImageByDogBreed(@Path("dogBreed") dogBreed: String): Response<RandomDogImageDto>

}
