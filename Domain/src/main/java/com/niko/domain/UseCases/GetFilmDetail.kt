package com.niko.domain.UseCases

import androidx.lifecycle.LiveData
import com.niko.domain.Models.FilmDetail
import com.niko.domain.Repository.FilmRepository

class GetFilmDetail(private val repository : FilmRepository) {
    fun getFIlmDetail() : LiveData<FilmDetail>{
        return repository.getFIlmDetail()
    }
}