package com.niko.data.API

import com.niko.domain.Models.FilmDetail
import com.niko.domain.Models.FilmItem
import com.niko.domain.Models.FilmList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
interface Main_API {

    @Headers("x-api-key: $API_KEY")
    @GET("/api/v2.2/films")
    suspend fun getFilms(@Query("order") order : String) : FilmList

    @Headers("x-api-key: $API_KEY")
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilm(@Path("id") id : Int) : FilmDetail
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://kinopoiskapiunofficial.tech")
    .addConverterFactory(GsonConverterFactory.create()).build()
val film_api = retrofit.create(Main_API::class.java)