package com.example.n2.http_service

import android.telephony.SmsManager
import com.example.n2.repository.SmsRepository
import com.example.n2.repository.room.SmsClass
import com.example.n2.utils.oldSendSmsFun
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SmsService2 : KoinComponent {

    private val smsRepository by inject<SmsRepository>()

    fun smsList(): List<SmsClass> = smsRepository.getItems()

    fun addSms(smsClass: SmsClass) : SmsClass {
        oldSendSmsFun(smsClass)
        return smsRepository.addItem(smsClass)
    }

    fun removeSms(smsClass: SmsClass) {
        smsRepository.removeItem(smsClass)
    }


}


