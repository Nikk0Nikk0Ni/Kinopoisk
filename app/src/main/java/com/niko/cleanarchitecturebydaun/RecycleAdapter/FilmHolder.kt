package com.niko.cleanarchitecturebydaun.RecycleAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niko.cleanarchitecturebydaun.databinding.FilmItemBinding
import com.niko.domain.Models.FilmItem
import com.squareup.picasso.Picasso

class FilmHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = FilmItemBinding.bind(view)
    fun bind(
        item: FilmItem,
        long: ((FilmItem) -> Unit)? = null,
        click: ((FilmItem) -> Unit)? = null
    ) {
        Picasso.get().load(item.posterUrl).into(binding.imgFilm)
        binding.tvName.text = shortString(item.nameRu)
        val genre = item.genres[0].genre.capitalize()
        binding.tvGanreYear.text = "$genre (${item.year})"
        itemView.setOnLongClickListener {
            long?.let { long ->
                long(item)
            }
            true
        }
        itemView.setOnClickListener {
            click?.let { click ->
                click(item)
            }
        }
        if(item.isFavourite){
            binding.like.visibility = View.VISIBLE
        }
    }

    fun shortString(string: String?): String? {
        if (string == null)
            return string
        return if (string.length > 20) {
            val outString = "${string.substring(0, 20)}..."
            outString
        } else
            string
    }
}