package com.niko.cleanarchitecturebydaun.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.niko.cleanarchitecturebydaun.R
import com.niko.cleanarchitecturebydaun.ViewModel.ViewModelForDetail
import com.niko.cleanarchitecturebydaun.databinding.FragmentFilmDetailBinding
import com.squareup.picasso.Picasso

class FilmDetail : Fragment() {
    private lateinit var binding : FragmentFilmDetailBinding
    private val viewModel : ViewModelForDetail by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmDetailBinding.inflate(inflater)
        initLayout()
        initBackBtn()
        return binding.root
    }

    private fun initBackBtn() {
        binding.backBtn.setOnClickListener{
            findNavController().navigate(R.id.filmListScreen)
        }
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

    override fun onPause() {
        super.onPause()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

}