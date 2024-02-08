package com.niko.domain.UseCases

import com.niko.domain.Models.FilmItem
import com.niko.domain.Repository.FilmRepository

class AddToFavouriteUseCase(private val repository: FilmRepository) {
    fun addToFav(itemId : Int){
        repository.addToFav(itemId)
    }
}