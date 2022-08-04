package com.soul.suhrob.soul.viewmodels

import androidx.lifecycle.ViewModel
import com.soul.suhrob.soul.repositories.MovieRepository

import javax.inject.Inject

/**
 * Created by Microstar on 19.08.2020.
 */

class MainActivityViewModel @Inject constructor(
    var movieRepository: MovieRepository
) : ViewModel() {
    var cardNumber: String = ""
}
