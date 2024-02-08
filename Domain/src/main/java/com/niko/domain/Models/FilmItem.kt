package com.niko.domain.Models

data class FilmItem(
    val kinopoiskId : Int,
    val nameRu : String,
    val posterUrl : String,
    val year : Int,
    val type : String,
    val genres : List<Genres>,
    var isFavourite : Boolean,
)
