package com.soul.suhrob.soul.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soul.suhrob.soul.databinding.ItemMovieCardBinding
import com.soul.suhrob.soul.network.models.main.new_movies.MovieResult
import com.soul.suhrob.soul.utils.Constants

/**
 * Created by Microstar on 019 19.08.21.
 */
class MovieCardAdapter : RecyclerView.Adapter<MovieCardAdapter.MovieCardViewHolder>() {
    private var itemClickListener: ((id: Long) -> Unit)? = null
    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    var data = mutableListOf<MovieResult>()

    fun setMovies(data: List<MovieResult>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    inner class MovieCardViewHolder(private val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: MovieResult) {
            binding.apply {
                name.text = data.originalTitle
                ratingBar.rating = data.voteAverage.toFloat()
                Glide.with(binding.root.context)
                    .load("${Constants.BASE_IMAGE_URL}${data.posterPath}")
                    .into(binding.image)
                itemView.setOnClickListener {
                    itemClickListener?.invoke(data.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieCardViewHolder(
        ItemMovieCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) =
        holder.bindData(data[position])
}