package com.niko.data.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.niko.data.API.film_api
import com.niko.domain.Models.FilmDetail
import com.niko.domain.Models.FilmItem
import com.niko.domain.Repository.FilmRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object FilmRepositoryImpl : FilmRepository {
    private val filmDetail = MutableLiveData<FilmDetail>()
    private var popList = listOf<FilmItem>()
    private val popListLD = MutableLiveData<List<FilmItem>>()
    private val favListLD = MutableLiveData<List<FilmItem>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val api_list = async { film_api.getFilms("RATING").items }
            popList = api_list.await()
            getFilmList()
        }
    }

    override fun getFilmList(): LiveData<List<FilmItem>> {
        popListLD.postValue(popList)
        return popListLD
    }

    override fun getFavList(): LiveData<List<FilmItem>> {
        favListLD.postValue(popList.filter { it.isFavourite })
        return favListLD
    }

    override fun addToFav(itemId: Int) {
        val element = popList.find { it.kinopoiskId == itemId }
        if (element != null && !element.isFavourite) {
            popList.find { it.kinopoiskId == itemId }?.isFavourite = true
            getFavList()
            getFilmList()
        }
    }

    override fun removeItem(itemId: Int) {
        val element = popList.find { it.kinopoiskId == itemId }
        if(element!=null && element.isFavourite){
            popList.find { it.kinopoiskId == itemId }?.isFavourite = false
            getFavList()
        }
    }

    override fun getFIlmDetail() : LiveData<FilmDetail> {
        return filmDetail
    }

    override fun setDitail(itemId: Int) {
        CoroutineScope(Dispatchers.IO).launch{
            val element = async{film_api.getFilm(itemId)}
            filmDetail.postValue(element.await())
        }
    }

}