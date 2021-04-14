package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handler.postDelayed(
            {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HelpFragment())
                    .commit()
            },
            HANDLER_TIME
        )
    }

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }

    companion object {
        private const val HANDLER_TIME: Long = 2000L
    }
}
