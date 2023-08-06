package com.example.n2.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.n2.repository.SmsRepositoryImp
import com.example.n2.repository.room.SmsClass

class SmsListFragmentViewModel(private val smsRepositoryImp: SmsRepositoryImp) : ViewModel() {

    fun getAllSms() : LiveData<List<SmsClass>> {
        return smsRepositoryImp.getAllSms()
    }

}