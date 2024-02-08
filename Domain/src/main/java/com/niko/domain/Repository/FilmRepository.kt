package com.niko.domain.Repository

import androidx.lifecycle.LiveData
import com.niko.domain.Models.FilmDetail
import com.niko.domain.Models.FilmItem
import com.niko.domain.Models.FilmList

interface FilmRepository {
    fun getFilmList() : LiveData<List<FilmItem>>
    fun getFavList() : LiveData<List<FilmItem>>
    fun addToFav(itemId : Int)
    fun removeItem(itemId : Int)
    fun getFIlmDetail() : LiveData<FilmDetail>
    fun setDitail(itemId : Int)
}