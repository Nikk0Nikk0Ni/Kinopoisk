package com.niko.domain.UseCases

import androidx.lifecycle.LiveData
import com.niko.domain.Models.FilmItem
import com.niko.domain.Repository.FilmRepository

class GetFavFIlmList(private val repository: FilmRepository){
    fun getFavList() : LiveData<List<FilmItem>>{
        return repository.getFavList()
    }
}