package com.niko.domain.UseCases

import com.niko.domain.Repository.FilmRepository

class SetFilmDetail(private val repository: FilmRepository) {
    fun setDetail(itemId : Int){
        repository.setDitail(itemId)
    }
}