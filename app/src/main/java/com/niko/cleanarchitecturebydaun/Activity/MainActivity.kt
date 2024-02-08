package com.niko.cleanarchitecturebydaun.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.util.Log
import android.view.Menu
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.niko.cleanarchitecturebydaun.Fragments.FavFilListScreen
import com.niko.cleanarchitecturebydaun.Fragments.FilmListScreen
import com.niko.cleanarchitecturebydaun.R
import com.niko.cleanarchitecturebydaun.databinding.ActivityMainBinding
import com.niko.data.API.film_api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initBtns()
        setContentView(binding.root)
    }

    private fun initBtns() = with(binding)
    {
        btnPopular.setOnClickListener {
            findNavController(fragmentContainerView.id).navigate(R.id.filmListScreen)
        }
        btnFavourite.setOnClickListener {
            findNavController(fragmentContainerView.id).navigate(R.id.favFilListScreen)
        }
    }

}