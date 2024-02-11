package com.niko.domain.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class FilmItem(
    val kinopoiskId : Int,
    val nameRu : String,
    val posterUrl : String,
    val year : Int,
    val type : String,
    val genres : List<Genres>,
    var isFavourite : Boolean,
    var id : Int = UNDEFIND_ID
)
{
    companion object{
        var UNDEFIND_ID = -1
    }
}
