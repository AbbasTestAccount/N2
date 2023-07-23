@file:Suppress("DEPRECATION")

package com.example.n2.utils

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.telephony.SmsManager
import android.telephony.SubscriptionManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.n2.SmsClass


fun sendSms(smsClass: SmsClass) {
    for (i in 0 until smsClass.recipients.size) {
        SmsManager.getSmsManagerForSubscriptionId(smsClass.sourceSim)
            .sendTextMessage(smsClass.recipients[i], null, smsClass.text, null, null)
    }
}

