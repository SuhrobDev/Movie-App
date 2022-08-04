package com.soul.suhrob.soul.fragments


import android.os.CountDownTimer
import com.soul.suhrob.soul.R
import com.soul.suhrob.soul.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreate() {

        object : CountDownTimer(3000, 100) {
            override fun onFinish() {
                navController.navigate(R.id.action_splashFragment_to_mainFragment)
            }

            override fun onTick(value: Long) {

            }
        }.start()

    }

}