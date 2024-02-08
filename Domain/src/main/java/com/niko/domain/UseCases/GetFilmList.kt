package com.niko.domain.UseCases

import androidx.lifecycle.LiveData
import com.niko.domain.Models.FilmItem
import com.niko.domain.Models.FilmList
import com.niko.domain.Repository.FilmRepository

class GetFilmList(private val repository: FilmRepository) {
    fun getList() : LiveData<List<FilmItem>> {
        return repository.getFilmList()
    }
}