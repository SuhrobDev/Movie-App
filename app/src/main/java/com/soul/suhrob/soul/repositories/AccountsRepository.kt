package com.soul.suhrob.soul.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import com.soul.suhrob.soul.dialogs.LoaderDialog
import com.soul.suhrob.soul.network.models.main.genres.MoviesGenresResponse
import com.soul.suhrob.soul.network.models.main.movie_detail.MovieDetailResponse
import com.soul.suhrob.soul.network.models.main.movie_trailers.MovieTrailerResponse
import com.soul.suhrob.soul.network.models.main.new_movies.MoviesResponse
import com.soul.suhrob.soul.network.models.main.person.PersonResponse
import com.soul.suhrob.soul.network.models.main.person.PersonResult
import com.soul.suhrob.soul.network.services.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private var service: MovieService,
    private var gson: Gson
) {
    var isLoading = MutableLiveData<Boolean>()
    var needAccessToken = MutableLiveData<Boolean>()

    val allPopularMovies = MutableLiveData<MoviesResponse>()
    val allTopRatedMovies = MutableLiveData<MoviesResponse>()
    val allUpcomingMovies = MutableLiveData<MoviesResponse>()

    fun getAllNewMovies(context: Context): LiveData<MoviesResponse> {
        val movies = MutableLiveData<MoviesResponse>()
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getAllNewMovies()
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                movies.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllTopRatedMovies->Ooops: Something else went wrong: $e")
            }
        }
        return movies
    }

    fun getAllMoviesGenres(context: Context): LiveData<MoviesGenresResponse> {
        val movies = MutableLiveData<MoviesGenresResponse>()
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getAllMoviesGenres()
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                movies.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllTopRatedMovies->Ooops: Something else went wrong: $e")
            }
        }
        return movies
    }

    fun getMoviesByGenre(context: Context, genreId: Int): LiveData<MoviesResponse> {
        val movies = MutableLiveData<MoviesResponse>()
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getMoviesByGenre(genreId)
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                movies.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getMoviesByGenre->Ooops: Something else went wrong: $e")
            }
        }
        return movies
    }

    fun getAllFamousPersons(context: Context): LiveData<PersonResponse> {
        val person = MutableLiveData<PersonResponse>()
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getAllFamousPersons()
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                person.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllFamousPersons->Ooops: Something else went wrong: $e")
            }
        }
        return person
    }

    fun getAllPopularMovies(context: Context): LiveData<MoviesResponse> {
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getAllPopularMovies()
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                allPopularMovies.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllFamousPersons->Ooops: Something else went wrong: $e")
            }
        }
        return allPopularMovies
    }

    fun getAllTopRatedMovies(context: Context): LiveData<MoviesResponse> {
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getAllTopRatedMovies()
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                allTopRatedMovies.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllTopRatedMovies->Ooops: Something else went wrong: $e")
            }
        }
        return allTopRatedMovies
    }

    fun getAlUpcomingMovies(context: Context): LiveData<MoviesResponse> {
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getAlUpcomingMovies()
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                allUpcomingMovies.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllTopRatedMovies->Ooops: Something else went wrong: $e")
            }
        }
        return allUpcomingMovies
    }

    fun getMovieDetailById(context: Context, movie_id: Long): LiveData<MovieDetailResponse> {
        val detail = MutableLiveData<MovieDetailResponse>()
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getMovieDetailById(movie_id)
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                detail.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllTopRatedMovies->Ooops: Something else went wrong: $e")
            }
        }
        return detail
    }

    fun getPersonDetailById(context: Context, person_id: Long): LiveData<PersonResult> {
        val detail = MutableLiveData<PersonResult>()
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getPersonDetailById(person_id)
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                detail.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllTopRatedMovies->Ooops: Something else went wrong: $e")
            }
        }
        return detail
    }

    fun getMovieTrailerListById(context: Context, movie_id: Long): LiveData<MovieTrailerResponse> {
        val detail = MutableLiveData<MovieTrailerResponse>()
        val dialog = LoaderDialog(context)
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getMovieTrailerListById(movie_id)
                when {
                    response.code() == 200 -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                detail.value = response.body()
                            }
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "HttpException: " + e.message())
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                }
                Log.d("TTTT", "getAllTopRatedMovies->Ooops: Something else went wrong: $e")
            }
        }
        return detail
    }
}