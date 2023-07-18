package com.example.n2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.WorkManager
import com.example.n2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFirstFrag()

        val workManager = WorkManager.getInstance(this)


    }

    private fun setFirstFrag() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mainFrame, SmsListFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}