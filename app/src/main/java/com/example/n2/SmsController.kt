package com.example.n2

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

fun Route.smsController() {
    val smsService by inject<SmsService2>()



    post("/sms/send") {
        val sms = call.receive<SmsClass>()
        smsService.addSms(sms)
        call.respond(HttpStatusCode.OK , SmsClass(sms.text,sms.sourceSim,sms.recipients))
    }

}