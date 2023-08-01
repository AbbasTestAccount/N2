package com.example.n2

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.n2.databinding.FragmentSmsListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class SmsRepositoryImp(private val database: SmsDao, context: Context) : SmsRepository, LifecycleObserver {
    private var idCount = 0
    private var smsList = ArrayList<SmsClass>()
    val binding = FragmentSmsListBinding.inflate(LayoutInflater.from(context))


    init {
        GlobalScope.launch(Dispatchers.Main){
            database.getAllSms().observeForever{
                idCount = it.size
                smsList = it as ArrayList<SmsClass>
            }
        }
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

    private fun setAdapter(){
        GlobalScope.launch(Dispatchers.Main) {
            database.getAllSms().observeForever { smsList ->
                val adapter = SmsAdapter(smsList as ArrayList<SmsClass>, null)
                binding.recyclerView.adapter = adapter
            }
        }
    }

}