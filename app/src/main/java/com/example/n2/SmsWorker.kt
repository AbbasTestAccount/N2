package com.example.n2

import android.content.Context
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class SmsWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val smsService : SmsService

    init {
        val retrofit = InitRetrofit().getClient()
        smsService = retrofit.create(SmsService::class.java)
    }


    override fun doWork(): Result {

        val call = smsService.sendSms(SmsClass(inputData.getString("text")!!, inputData.getInt("sourceSim",1),
            inputData.getStringArray("recipients")!!
        ))
        call.enqueue(object : Callback<SmsClass>{
            override fun onResponse(call: Call<SmsClass>, response: retrofit2.Response<SmsClass>) {
                Toast.makeText(context, "sms sent", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<SmsClass>, t: Throwable) {
                Toast.makeText(context, "sms failed", Toast.LENGTH_SHORT).show()
            }


        })

        return Result.success(workDataOf("isSend" to "success"))


    }
}