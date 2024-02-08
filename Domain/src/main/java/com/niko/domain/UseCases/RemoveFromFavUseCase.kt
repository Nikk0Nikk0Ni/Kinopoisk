package com.niko.domain.UseCases

import com.niko.domain.Models.FilmItem
import com.niko.domain.Repository.FilmRepository

class RemoveFromFavUseCase(private val repository: FilmRepository) {
    fun removeItem(itemId : Int){
        repository.removeItem(itemId)
    }
}