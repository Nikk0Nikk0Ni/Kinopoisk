package com.niko.domain.Models

data class FilmDetail(
    val nameRu : String,
    val posterUrl : String,
    val description : String,
    val genres: List<Genres>,
    val countries : List<Countries>
)
