package com.example.n2.http_service

import com.example.n2.repository.room.SmsClass
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SendSmsFunTest {

    @Test
    fun oldSendSmsFunTest() {
        val smsClass = SmsClass(1,"dasdasdsa",1, arrayOf("09123298282"),"")
        val x = oldSendSmsFun(smsClass)
        assertThat(x).isEqualTo(0)
    }
}