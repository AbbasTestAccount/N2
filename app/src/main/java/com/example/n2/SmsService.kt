package com.example.n2

import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SmsService {

    @POST("sms/send")
    fun sendSms(@Body body : SmsClass): Call<SmsClass>
}