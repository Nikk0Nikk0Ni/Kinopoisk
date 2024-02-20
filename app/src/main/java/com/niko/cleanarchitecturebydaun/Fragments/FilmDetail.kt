package com.niko.cleanarchitecturebydaun.Fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.niko.cleanarchitecturebydaun.R
import com.niko.cleanarchitecturebydaun.ViewModel.ViewModelForDetail
import com.niko.cleanarchitecturebydaun.databinding.FragmentFilmDetailBinding
import com.squareup.picasso.Picasso

class FilmDetail : Fragment(), MenuProvider {
    private lateinit var binding : FragmentFilmDetailBinding
    private val viewModel : ViewModelForDetail by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmDetailBinding.inflate(inflater)
        initLayout()
        activity?.addMenuProvider(this,viewLifecycleOwner)
        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu,menu)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolBarName = SpannableString(getString(R.string.detail))
        toolBarName.setSpan(StyleSpan(Typeface.BOLD), 0, toolBarName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        activity?.title = toolBarName
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            android.R.id.home -> findNavController().navigate(R.id.filmListScreen)
        }
        return true
    }

    private fun initLayout(){
        viewModel.getfilmDetail.observe(viewLifecycleOwner){
            binding.apply {
                Picasso.get().load(it.posterUrl).into(imageView)
                tvFilmName.text = it.nameRu
                tvFilmDesc.text = it.description
                val strCountries = mutableListOf<String>()
                it.countries.forEach{
                    strCountries.add(it.country)
                }
                val strGenres = mutableListOf<String>()
                it.genres.forEach{
                    strGenres.add(it.genre)
                }
                Log.e("AUF","$strCountries     $strGenres")
                tvCountries.text = strCountries.joinToString(separator = ", ")
                tvGenres.text = strGenres.joinToString(separator = ", ")
                tvFilmDesc.text = it.description
            }
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}