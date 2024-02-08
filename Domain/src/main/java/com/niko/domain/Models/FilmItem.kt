package com.niko.domain.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Films")
data class FilmItem(
    @PrimaryKey
    val kinopoiskId : Int,
    @ColumnInfo
    val nameRu : String,
    @ColumnInfo
    val posterUrl : String,
    @ColumnInfo
    val year : Int,
    @ColumnInfo
    val type : String,
    @ColumnInfo
    val genres : List<Genres>,
    @ColumnInfo
    var isFavourite : Boolean,
)
