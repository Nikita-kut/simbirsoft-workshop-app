package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding: FragmentSplashScreenBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Handler().postDelayed(
            {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, ProfileFragment())
                    ?.commit()
            },
            2000
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
