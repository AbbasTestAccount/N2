package com.example.n2.not_used

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.telephony.SmsManager
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import retrofit2.Call
import retrofit2.Callback

//class SmsWorker(private val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
//
//    private val smsService : SmsService
//
//    init {
//        val retrofit = InitRetrofit().getClient()
//        smsService = retrofit.create(SmsService::class.java)
//    }
//
//
//    override fun doWork(): Result {
//
//        showToast("oomad")
//        val call = smsService.sendSms(SmsClass(inputData.getString("text")!!, inputData.getInt("sourceSim",1),
//            inputData.getStringArray("recipients")!!
//        ))
//        call.enqueue(object : Callback<SmsClass>{
//            override fun onResponse(call: Call<SmsClass>, response: retrofit2.Response<SmsClass>) {
//                showToast("sms sent")
//
//            }
//
//            override fun onFailure(call: Call<SmsClass>, t: Throwable) {
//                showToast("sms failed  ${t.message}")
//            }
//
//
//        })
//
//
//        return Result.success(workDataOf("isSend" to "success"))
//
//
//    }
//
//    private fun showToast(message: String) {
//
//        Handler(Looper.getMainLooper()).post {
//            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//        }
//
//    }
//}