package com.niko.cleanarchitecturebydaun.Fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
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
import com.niko.cleanarchitecturebydaun.ViewModel.ViewModelForFav
import com.niko.cleanarchitecturebydaun.databinding.FragmentFavFilListScreenBinding

class FavFilListScreen : Fragment(), MenuProvider {
    private lateinit var binding : FragmentFavFilListScreenBinding
    private val adapter = FilmAdapter()
    private val viewModel : ViewModelForFav by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavFilListScreenBinding.inflate(inflater)
        activity?.addMenuProvider(this,viewLifecycleOwner)
        initRecView()
        return binding.root
    }

    private fun initRecView() {
        binding.recView.adapter = adapter
        adapter.longTap = {
            viewModel.removeFilm(it.kinopoiskId)
        }
        adapter.clickTap = {
            viewModel.setDetail(it.kinopoiskId)
            findNavController().navigate(R.id.action_favFilListScreen_to_filmDetail)
        }
        viewModel.list.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu,menu)
        val toolBarName = SpannableString(getString(R.string.favourite))
        toolBarName.setSpan(StyleSpan(Typeface.BOLD), 0, toolBarName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        activity?.title = toolBarName
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

}