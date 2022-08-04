package com.soul.suhrob.soul.network.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.soul.suhrob.soul.network.models.main.genres.MoviesGenresResponse
import com.soul.suhrob.soul.network.models.main.movie_detail.MovieDetailResponse
import com.soul.suhrob.soul.network.models.main.movie_trailers.MovieTrailerResponse
import com.soul.suhrob.soul.network.models.main.new_movies.MoviesResponse
import com.soul.suhrob.soul.network.models.main.person.PersonResponse
import com.soul.suhrob.soul.network.models.main.person.PersonResult
import com.soul.suhrob.soul.utils.Constants


interface MovieService {

    //    @POST("profile/account/current")
//    suspend fun createAccount(@Query("currency") currency: String): Response<MyAccountResponse>
//
//    @GET("profile/account/transactions/{branch}/{account}")
//    suspend fun deleteAccount(
//        @Path("branch") branch: String,
//        @Path("account") account: String,
//        @Query("closeReason ") closeReason : String,
//    ): Response<List<MyAccountMonitoringResponse>>

    @GET("movie/now_playing")
    suspend fun getAllNewMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("genre/movie/list")
    suspend fun getAllMoviesGenres(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesGenresResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("trending/person/week")
    suspend fun getAllFamousPersons(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<PersonResponse>

    @GET("movie/popular")
    suspend fun getAllPopularMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getAllTopRatedMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getAlUpcomingMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MovieDetailResponse>

    @GET("person/{person_id}")
    suspend fun getPersonDetailById(
        @Path("person_id") person_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<PersonResult>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailerListById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MovieTrailerResponse>
}