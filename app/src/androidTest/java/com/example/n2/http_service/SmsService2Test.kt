package com.example.n2.http_service

import android.annotation.SuppressLint
import com.example.n2.repository.SmsRepository
import com.example.n2.repository.room.SmsClass

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.google.common.truth.Truth.assertThat

class SmsService2Test : KoinComponent {
    private val smsRepository by inject<SmsRepository>()


    private lateinit var smsService2: SmsService2

    @Before
    fun setUp() {
        smsService2 = SmsService2()
    }

    @After
    fun tearDown() {
    }

    @SuppressLint("CheckResult")
    @Test
    fun addSms() {
        val smsClass = SmsClass(1, "sasas", 1, arrayOf("09122928321"), "")
        smsService2.addSms(smsClass)

        assertThat(smsService2.smsList()).contains(smsClass)

    }

    @Test
    fun removeSms() {
    }
}