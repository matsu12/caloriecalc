package com.matsu.dai.caloriecalc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.matsu.dai.caloriecalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel = MainViewModel(application)
        binding.viewMode = mainViewModel
        binding.lifecycleOwner = this
    }

}