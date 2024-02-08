package com.niko.cleanarchitecturebydaun.ViewModel

import androidx.lifecycle.ViewModel
import com.niko.data.Repository.FilmRepositoryImpl
import com.niko.domain.Models.FilmItem
import com.niko.domain.UseCases.GetFavFIlmList
import com.niko.domain.UseCases.GetFilmDetail
import com.niko.domain.UseCases.GetFilmList
import com.niko.domain.UseCases.RemoveFromFavUseCase
import com.niko.domain.UseCases.SetFilmDetail

class ViewModelForFav : ViewModel() {
    private val repository = FilmRepositoryImpl
    private val getFavList = GetFavFIlmList(repository)
    private val removeFilm = RemoveFromFavUseCase(repository)
    private val setFilmDetail = SetFilmDetail(repository)

    val list = getFavList.getFavList()

    fun removeFilm(itemId : Int){
        removeFilm.removeItem(itemId)
    }

    fun setDetail(itemId : Int){
        setFilmDetail.setDetail(itemId)
    }
}