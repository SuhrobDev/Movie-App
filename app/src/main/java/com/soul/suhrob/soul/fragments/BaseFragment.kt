package com.soul.suhrob.soul.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import com.soul.suhrob.soul.utils.Inflate
import com.soul.suhrob.soul.utils.SharedPreferencesHelper
import com.soul.suhrob.soul.utils.dismissKeyboard
import com.soul.suhrob.soul.viewmodels.DaggerViewModelFactory
import com.soul.suhrob.soul.viewmodels.MainActivityViewModel
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : DaggerFragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var viewModel: MainActivityViewModel
    lateinit var navController: NavController

    @Inject
    lateinit var shared: SharedPreferencesHelper

    @Inject
    lateinit var providerFactory: DaggerViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dismissKeyboard(requireActivity())
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProviders.of(
            requireActivity(),
            providerFactory
        )[MainActivityViewModel::class.java]
        onViewCreate()
    }

    abstract fun onViewCreate()

}
