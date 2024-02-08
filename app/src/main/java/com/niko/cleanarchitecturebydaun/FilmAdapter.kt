package com.niko.cleanarchitecturebydaun

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.niko.cleanarchitecturebydaun.databinding.FilmItemBinding
import com.niko.domain.Models.FilmItem
import com.squareup.picasso.Picasso

class FilmAdapter : ListAdapter<FilmItem,FilmAdapter.FilmHolder>(Comporator()) {
    var clickTap : ((FilmItem) -> Unit)? = null
    var longTap : ((FilmItem) -> Unit)? = null
    class FilmHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding = FilmItemBinding.bind(view)
        fun bind(item : FilmItem, long : ((FilmItem) -> Unit)? = null, click : ((FilmItem)-> Unit)? = null){
            Picasso.get().load(item.posterUrl).into(binding.imgFilm)
            binding.tvName.text = shortString(item.nameRu)
            val genre = item.genres[0].genre.capitalize()
            binding.tvGanreYear.text = "$genre (${item.year})"
            itemView.setOnLongClickListener{
                long?.let {
                        long -> long(item)
                }
                true
            }
            itemView.setOnClickListener{
                click?.let {
                    click -> click(item)
                }
            }
        }
        fun shortString(string: String?) : String?{
            if(string == null)
                return string
            return if(string.length>20) {
                val outString = "${string.substring(0,20)}..."
                outString
            }
            else
                string
        }
    }
    class Comporator : DiffUtil.ItemCallback<FilmItem>() {
        override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
            return oldItem.kinopoiskId == newItem.kinopoiskId
        }

        override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item,parent,false)
        return FilmHolder(view)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(getItem(position),longTap,clickTap)
    }


}