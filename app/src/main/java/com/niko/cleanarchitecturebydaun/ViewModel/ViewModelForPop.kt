package com.niko.cleanarchitecturebydaun.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niko.data.Repository.FilmRepositoryImpl
import com.niko.domain.Models.FilmItem
import com.niko.domain.UseCases.AddToFavouriteUseCase
import com.niko.domain.UseCases.GetFilmDetail
import com.niko.domain.UseCases.GetFilmList
import com.niko.domain.UseCases.SetFilmDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelForPop : ViewModel() {
    private val repository = FilmRepositoryImpl
    private val getFilmList = GetFilmList(repository)
    private val addFilmItem = AddToFavouriteUseCase(repository)
    private val setFilmDetail = SetFilmDetail(repository)

    val shopList = getFilmList.getList()

    fun addToFavoutite(itemId : Int){
        addFilmItem.addToFav(itemId)
    }

    fun setDetail(itemId : Int){
        setFilmDetail.setDetail(itemId)
    }

}