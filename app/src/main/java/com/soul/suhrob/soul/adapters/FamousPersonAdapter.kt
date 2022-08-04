package com.soul.suhrob.soul.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soul.suhrob.soul.databinding.ItemFamousPersonBinding
import com.soul.suhrob.soul.network.models.main.person.PersonResult
import com.soul.suhrob.soul.utils.Constants

/**
 * Created by Microstar on 019 19.08.21.
 */
class FamousPersonAdapter : RecyclerView.Adapter<FamousPersonAdapter.MovieCardViewHolder>() {

    private var itemClickListener: ((id: Long) -> Unit)? = null

    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    var data = mutableListOf<PersonResult>()

    fun setPersons(data: List<PersonResult>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    inner class MovieCardViewHolder(private val binding: ItemFamousPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: PersonResult) {
            binding.apply {
                name.text = data.name
                Glide.with(binding.root.context)
                    .load("${Constants.BASE_IMAGE_URL}${data.profilePath}")
                    .into(binding.image)
                itemView.setOnClickListener {
                    itemClickListener?.invoke(data.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieCardViewHolder(
        ItemFamousPersonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) =
        holder.bindData(data[position])
}