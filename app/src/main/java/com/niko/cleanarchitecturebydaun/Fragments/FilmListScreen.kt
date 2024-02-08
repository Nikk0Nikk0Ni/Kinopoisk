package com.niko.cleanarchitecturebydaun.Fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.niko.cleanarchitecturebydaun.RecycleAdapter.FilmAdapter
import com.niko.cleanarchitecturebydaun.R
import com.niko.cleanarchitecturebydaun.ViewModel.ViewModelForPop
import com.niko.cleanarchitecturebydaun.databinding.FragmentFilmListScreenBinding

class FilmListScreen : Fragment(), MenuProvider{
    private lateinit var binding : FragmentFilmListScreenBinding
    private val viewModel : ViewModelForPop by activityViewModels()
    private val adapter = FilmAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmListScreenBinding.inflate(inflater)
        initRecView()
        activity?.addMenuProvider(this,viewLifecycleOwner)
        return binding.root
    }

    private fun initRecView() {
        binding.recView.adapter = adapter
        adapter.longTap = {
            viewModel.addToFavoutite(it.kinopoiskId)
            adapter.notifyItemChanged(it.id)
        }
        adapter.clickTap = {
            viewModel.setDetail(it.kinopoiskId)
            findNavController().navigate(R.id.action_filmListScreen_to_filmDetail)
        }
        viewModel.shopList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        increasePool()
    }

    private fun increasePool() {
        binding.recView.recycledViewPool.setMaxRecycledViews(R.layout.film_item,10)
        binding.recView.recycledViewPool.setMaxRecycledViews(R.layout.fav_film_item,10)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu,menu)
        val toolBarName = SpannableString(getString(R.string.popular))
        toolBarName.setSpan(StyleSpan(Typeface.BOLD), 0, toolBarName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        activity?.title = toolBarName
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

}