package com.example.n2

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.n2.databinding.ActivityMainBinding
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val workManager = WorkManager.getInstance(this)
//
//        val httpWorker = OneTimeWorkRequest.from(HttpWorker::class.java)
//
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



        //            stopService(Intent(this, HttpService()::class.java))
        if (checkPort()) {
            Toast.makeText(this, "saaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show()
        }else{
            startService(Intent(this, HttpService()::class.java))
        }




        setFirstFrag()


    }

//    override fun onDestroy() {
//        super.onDestroy()
//        startService(Intent(this, HttpService()::class.java))
//        Log.e("Abbas2", "onDestroy: ")
//    }

    private fun setFirstFrag() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mainFrame, SmsListFragment())
        transaction.commit()
    }


}

fun checkPort(): Boolean {
    val socket = Socket()
    try {
        socket.bind(InetSocketAddress(PORT))
        socket.close()
    } catch (e: IOException) {
        return true
    }
    return false
}

