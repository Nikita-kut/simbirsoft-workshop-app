package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.kut.android.simbirsoft_workshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, SplashScreenFragment())
                .commit()
        }
    }
}
