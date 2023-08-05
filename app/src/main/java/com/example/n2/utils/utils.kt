@file:Suppress("DEPRECATION")

package com.example.n2.utils

import android.telephony.SmsManager
import com.example.n2.repository.room.SmsClass


fun sendSms(smsClass: SmsClass) {
    for (i in 0 until smsClass.recipients.size) {
        SmsManager.getSmsManagerForSubscriptionId(smsClass.sourceSim)
            .sendTextMessage(smsClass.recipients[i], null, smsClass.text, null, null)
    }
}

