package com.niko.cleanarchitecturebydaun.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.niko.data.Repository.FilmRepositoryImpl
import com.niko.domain.Models.FilmDetail
import com.niko.domain.UseCases.GetFilmDetail

class ViewModelForDetail : ViewModel() {
    private val repository = FilmRepositoryImpl
    private val filmDetail = GetFilmDetail(repository)

    val getfilmDetail = filmDetail.getFIlmDetail()
}