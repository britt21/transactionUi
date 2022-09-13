package com.example.bankly_mobile

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.bankly_mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        val laypager = binding.vpager

        laypager.adapter = adapter


        binding.tabs.setupWithViewPager(laypager)
        setContentView(binding.root)
    }
}