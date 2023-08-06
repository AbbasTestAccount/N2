@file:Suppress("DEPRECATION")

package com.example.n2.utils

import android.telephony.SmsManager
import com.example.n2.http_service.PORT
import com.example.n2.repository.room.SmsClass
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


fun sendSms(smsClass: SmsClass) {
    for (i in 0 until smsClass.recipients.size) {
        SmsManager.getSmsManagerForSubscriptionId(smsClass.sourceSim)
            .sendTextMessage(smsClass.recipients[i], null, smsClass.text, null, null)
    }
}

fun oldSendSmsFun(smsClass: SmsClass) : Int {
    var did_not_send = 0
    val smsManager = SmsManager.getDefault()
    val message = smsClass.text
//        val sentIntent = PendingIntent.getBroadcast(context, 0, Intent("SMS_SENT"), 0)
//        val deliveredIntent = PendingIntent.getBroadcast(context, 0, Intent("SMS_DELIVERED"), 0)

    for (i in 0 until smsClass.recipients.size){
        try {
            smsManager.sendTextMessage(smsClass.recipients[i], null, message, null, null)
        }catch (e : Exception){
            did_not_send++
        }
    }
    return did_not_send
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

