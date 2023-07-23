package com.example.n2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.n2.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        startService(Intent(this, HttpService::class.java))

        setFirstFrag()


    }

    private fun setFirstFrag() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mainFrame, SmsListFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}