package com.soul.suhrob.soul.fragments

import android.content.Intent
import com.bumptech.glide.Glide
import com.soul.suhrob.soul.YoutubeActivity
import com.soul.suhrob.soul.databinding.FragmentMovieDetailBinding
import com.soul.suhrob.soul.utils.Constants


class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {
    override fun onViewCreate() {
        getMovieDetailById(requireArguments().getLong("MOVIE_ID", 0))
    }

    private fun getMovieDetailById(movieId: Long) {
        viewModel.movieRepository.getMovieDetailById(requireContext(), movieId)
            .observe(this, { result ->
                binding.apply {
                    ratingNumber.text = "${result.voteAverage}"
                    ratingBar.rating = result.voteAverage.toFloat()
                    content.text = result.overview
                    Glide.with(binding.root.context)
                        .load("${Constants.BASE_IMAGE_URL}${result.backdropPath}")
                        .into(binding.image)

                    playBtn.setOnClickListener {
                        viewModel.movieRepository.getMovieTrailerListById(
                            requireContext(),
                            result.id
                        ).observe(this@MovieDetailFragment, {
                            val i = Intent(requireContext(), YoutubeActivity::class.java)
                            i.putExtra("YOUTUBE_VIDEO_ID", it.results.first().key)
                            startActivity(i)
                        })
                    }
                }
            })
    }

}