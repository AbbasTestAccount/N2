package com.example.n2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.n2.databinding.ActivityMainBinding
import com.example.n2.fragments.SmsListFragment
import com.example.n2.http_service.HttpService
import com.example.n2.utils.checkPort



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val workManager = WorkManager.getInstance(this)
//        val httpWorker = OneTimeWorkRequest.from(HttpWorker::class.java)
//        workManager.enqueue(httpWorker)

        val sharedPreferences = getSharedPreferences("makeNew", MODE_PRIVATE)

        if (sharedPreferences.getBoolean("first run", true)) {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECEIVE_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS),
                    111
                )
            }
            sharedPreferences.edit().putBoolean("first run", false).apply()
        }

        //stopService(Intent(this, HttpService()::class.java))

        if (checkPort()) {
            Toast.makeText(this, "saaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show()
        }else{
            startService(Intent(this, HttpService()::class.java))
        }

        setFirstFrag()

    }

    private fun setFirstFrag() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mainFrame, SmsListFragment())
        transaction.commit()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        startService(Intent(this, HttpService()::class.java))
//        Log.e("Abbas2", "onDestroy: ")
//    }

}



