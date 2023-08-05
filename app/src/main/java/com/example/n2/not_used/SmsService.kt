package com.example.n2.not_used

import com.example.n2.repository.room.SmsClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SmsService {

    @POST("sms/send")
    fun sendSms(@Body body : SmsClass): Call<SmsClass>

}