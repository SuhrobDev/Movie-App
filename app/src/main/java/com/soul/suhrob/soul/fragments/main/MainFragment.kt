package com.soul.suhrob.soul.fragments.main

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.tabs.TabLayout
import com.soul.suhrob.soul.R
import com.soul.suhrob.soul.adapters.FamousPersonAdapter
import com.soul.suhrob.soul.adapters.MovieCardAdapter
import com.soul.suhrob.soul.databinding.FragmentMainBinding
import com.soul.suhrob.soul.fragments.BaseFragment
import com.soul.suhrob.soul.utils.Constants
import com.soul.suhrob.soul.utils.toast

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    lateinit var movieCardAdapter: MovieCardAdapter
    lateinit var aLLMovieCardAdapter: MovieCardAdapter
    lateinit var famousPersonAdapter: FamousPersonAdapter
    override fun onViewCreate() {
        setHasOptionsMenu(true)

        movieCardAdapter = MovieCardAdapter()
        movieCardAdapter.setItemClickListener {
            val bundle = bundleOf("MOVIE_ID" to it)
            navController.navigate(R.id.action_mainFragment_to_movieDetailFragment, bundle)
        }
        binding.movieCardList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.movieCardList.adapter = movieCardAdapter

        famousPersonAdapter = FamousPersonAdapter()
        famousPersonAdapter.setItemClickListener {
            val bundle = bundleOf("ACTOR_ID" to it)
            navController.navigate(R.id.action_mainFragment_to_actorFragment, bundle)
        }
        binding.famousPersonsList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.famousPersonsList.adapter = famousPersonAdapter

        aLLMovieCardAdapter = MovieCardAdapter()
        aLLMovieCardAdapter.setItemClickListener {
            val bundle = bundleOf("MOVIE_ID" to it)
            navController.navigate(R.id.action_mainFragment_to_movieDetailFragment, bundle)
        }
        binding.allMoviesList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.allMoviesList.adapter = aLLMovieCardAdapter

        getAllNewMovies()
        getAllMoviesGenres()
        getAllFamousPersons()
        getAllMovies()

    }

    private fun getAllMovies() {

        binding.allMoviesTab.addTab(binding.allMoviesTab.newTab().setText("POPULAR"))
        binding.allMoviesTab.addTab(binding.allMoviesTab.newTab().setText("TOP RATED"))
        binding.allMoviesTab.addTab(binding.allMoviesTab.newTab().setText("UPCOMING"))

        binding.allMoviesTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        viewModel.movieRepository.allPopularMovies.observe(this@MainFragment, {
                            aLLMovieCardAdapter.setMovies(it.results)
                        })
                    }
                    1 -> {
                        viewModel.movieRepository.allTopRatedMovies.observe(this@MainFragment, {
                            aLLMovieCardAdapter.setMovies(it.results)
                        })
                    }
                    2 -> {
                        viewModel.movieRepository.allUpcomingMovies.observe(this@MainFragment, {
                            aLLMovieCardAdapter.setMovies(it.results)
                        })
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        viewModel.movieRepository.getAllPopularMovies(requireContext()).observe(this, {
            aLLMovieCardAdapter.setMovies(it.results)
        })
        viewModel.movieRepository.getAllTopRatedMovies(requireContext())
        viewModel.movieRepository.getAlUpcomingMovies(requireContext())
    }

    private fun getAllFamousPersons() {
        viewModel.movieRepository.getAllFamousPersons(requireContext())
            .observe(this, { result ->
                famousPersonAdapter.setPersons(result.results)
            })
    }

    private fun getAllNewMovies() {
        viewModel.movieRepository.getAllNewMovies(requireContext()).observe(this, { result ->
            val imageList = ArrayList<SlideModel>() // Create image list
            val positions = ArrayList<Long>() // Create image list
            result?.results?.forEach { movies ->
                positions.add(movies.id)
                imageList.add(
                    SlideModel(
                        "${Constants.BASE_IMAGE_URL}${movies.backdropPath}",
                        movies.originalTitle
                    )
                )
            }
            binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
            binding.imageSlider.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    val bundle = bundleOf("MOVIE_ID" to positions[position])
                    navController.navigate(R.id.action_mainFragment_to_movieDetailFragment, bundle)
                }
            })
        })
    }

    private fun getAllMoviesGenres() {
//        var moviesGenres= ArrayList<Genre>()
        viewModel.movieRepository.getAllMoviesGenres(requireContext()).observe(this, { result ->
            result?.genres?.forEach { movies ->
                binding.tabLayout.addTab(
                    binding.tabLayout.newTab().setText(movies.name).setId(movies.id)
                )
            }
            getMoviesByGenre(result.genres.first().id)
            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    getMoviesByGenre(tab!!.id)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        })
    }

    private fun getMoviesByGenre(genreId: Int) {
        viewModel.movieRepository.getMoviesByGenre(requireContext(), genreId)
            .observe(this, { result ->
                movieCardAdapter.setMovies(result.results)
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            toast(requireContext(), "Search")
            return super.onOptionsItemSelected(item)
        }

        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        binding.imageSlider.stopSliding()
        super.onStop()
    }
}