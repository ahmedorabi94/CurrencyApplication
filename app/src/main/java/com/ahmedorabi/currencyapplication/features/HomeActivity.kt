package com.ahmedorabi.currencyapplication.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmedorabi.currencyapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}