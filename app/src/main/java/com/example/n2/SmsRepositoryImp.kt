package com.example.n2

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import com.example.n2.databinding.FragmentSmsListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SmsRepositoryImp(private val database: SmsDao, context: Context) : SmsRepository {
    private var idCount = 0
    private val smsList = ArrayList<SmsClass>()
    val binding = FragmentSmsListBinding.inflate(LayoutInflater.from(context))



    override fun addItem(smsItem: SmsClass): SmsClass {
        GlobalScope.launch(Dispatchers.Main){
            database.getAllSms().observeForever{
                idCount = it.size
                Log.e("idCount", idCount.toString() )
            }
        }
        Thread.sleep(1000)
        val newSms = smsItem.copy(id = ++idCount);
        smsList.add(newSms)
        database.addSms(newSms)
        GlobalScope.launch(Dispatchers.Main){
            database.getAllSms().observeForever{
                Log.e("smsList", it.toString() )
                var adapter = SmsAdapter(it as ArrayList<SmsClass>)
                binding.recyclerView.adapter = adapter

            }
        }
        return newSms

    }

    override fun removeItem(smsItem: SmsClass): SmsClass {
        smsList.remove(smsItem)
        return smsItem
    }

    override fun getItems(): ArrayList<SmsClass> {
        return smsList
    }

}