package com.example.sajhadabali.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sajhadabali.R
import kotlinx.coroutines.*

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityScope.launch {
            delay(3000)

            findNavController().navigate(R.id.action_nav_splash_to_nav_top)
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}