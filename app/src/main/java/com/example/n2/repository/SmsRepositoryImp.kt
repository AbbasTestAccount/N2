package com.example.n2.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import com.example.n2.repository.room.SmsClass
import com.example.n2.repository.room.SmsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class SmsRepositoryImp(private val database: SmsDao) : SmsRepository, LifecycleObserver {
    private var idCount = 0
    var smsList = ArrayList<SmsClass>()


    init {
        GlobalScope.launch(Dispatchers.Main){
            database.getAllSms().observeForever{
                idCount = it.size
                smsList = it as ArrayList<SmsClass>
            }
        }
    }

    fun getAllSms() : LiveData<List<SmsClass>>{
        return database.getAllSms()
    }


    @SuppressLint("SimpleDateFormat")
    override fun addItem(smsItem: SmsClass): SmsClass {
        idCount = smsList.size
        Thread.sleep(1000)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentDateAndTime = sdf.format(Date())
        smsItem.date = currentDateAndTime
        val newSms = smsItem.copy(id = idCount++);
        smsList.add(0,newSms)
        database.addSms(newSms)
        Log.e("idCount", smsItem.toString() )
        //setAdapter()
        return newSms

    }

    override fun removeItem(smsItem: SmsClass): SmsClass {
        idCount = smsList.size - 1
        smsList.remove(smsItem)
        database.deleteSms(smsItem)
        return smsItem
    }

    override fun getItems(): ArrayList<SmsClass> {
        return smsList
    }

}