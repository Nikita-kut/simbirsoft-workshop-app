package com.nikita.kut.android.simbirsoft_workshop.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.kut.android.simbirsoft_workshop.databinding.ActivityMainBinding
import com.nikita.kut.android.simbirsoft_workshop.feature.main.presentation.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    binding.fragmentContainer.id,
                    MainFragment(), MainFragment.MAIN_FRAGMENT_TAG
                )
                .commit()
        }
    }

    override fun onBackPressed() {
        val backStackCount =
            supportFragmentManager.findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG)?.childFragmentManager?.backStackEntryCount
        if (backStackCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG)?.childFragmentManager?.popBackStack()
        }
    }

}
