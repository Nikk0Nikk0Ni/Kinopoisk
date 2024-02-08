package com.niko.cleanarchitecturebydaun.RecycleAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.niko.cleanarchitecturebydaun.R
import com.niko.domain.Models.FilmItem

class FilmAdapter : ListAdapter<FilmItem, FilmHolder>(Comporator()) {
    var clickTap : ((FilmItem) -> Unit)? = null
    var longTap : ((FilmItem) -> Unit)? = null

    class Comporator : DiffUtil.ItemCallback<FilmItem>() {
        override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
            return oldItem.kinopoiskId == newItem.kinopoiskId
        }

        override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return FilmHolder(view)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(getItem(position),longTap,clickTap)
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).isFavourite) R.layout.fav_film_item else R.layout.film_item
    }


}