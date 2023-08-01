package com.example.n2

import android.app.PendingIntent
import android.telephony.SmsManager
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

fun oldSendSmsFun(smsClass: SmsClass) {
    val smsManager = SmsManager.getDefault()
    val message = smsClass.text
//        val sentIntent = PendingIntent.getBroadcast(context, 0, Intent("SMS_SENT"), 0)
//        val deliveredIntent = PendingIntent.getBroadcast(context, 0, Intent("SMS_DELIVERED"), 0)

    for (i in 0 until smsClass.recipients.size){
        smsManager.sendTextMessage(smsClass.recipients[i], null, message, null, null)
    }
}
