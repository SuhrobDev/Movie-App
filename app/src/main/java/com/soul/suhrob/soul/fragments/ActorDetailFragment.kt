package com.soul.suhrob.soul.fragments

import com.bumptech.glide.Glide
import com.soul.suhrob.soul.databinding.FragmentActorDetailBinding
import com.soul.suhrob.soul.utils.Constants

class ActorDetailFragment :
    BaseFragment<FragmentActorDetailBinding>(FragmentActorDetailBinding::inflate) {
    override fun onViewCreate() {
        getActorDetailById(requireArguments().getLong("ACTOR_ID", 0))
    }

    private fun getActorDetailById(id: Long) {
        viewModel.movieRepository.getPersonDetailById(requireContext(), id)
            .observe(this) { result ->
                binding?.apply {
                    name.text = result.name
                    ratingBar.rating = result.popularity.toFloat() * 10
                    birthday.text = result.birthday
                    place.text = result.place_of_birth
                    Glide.with(binding!!.root.context)
                        .load("${Constants.BASE_IMAGE_URL}${result.profilePath}")
                        .into(binding!!.image)
                }
            }
    }
}