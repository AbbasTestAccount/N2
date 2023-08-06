package com.example.n2.repository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.n2.repository.room.MyDatabase
import com.example.n2.repository.room.SmsClass
import com.example.n2.repository.room.SmsDao
import com.google.common.truth.Truth.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SmsRepositoryImpTest {
    private lateinit var database: MyDatabase
    private lateinit var dao: SmsDao
    private lateinit var smsRepositoryImp: SmsRepositoryImp

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = MyDatabase.getDatabase(ApplicationProvider.getApplicationContext())
        dao = database.smsDao
        smsRepositoryImp = SmsRepositoryImp(dao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun addItem() {
        val smsClass = SmsClass(0, "sadadas", 1, arrayOf("09182121212"), "")
        val newSmsClass = smsRepositoryImp.addItem(smsClass)

        val changedSms = smsClass.copy(id = smsClass.id!! + 1, date = newSmsClass.date)
        assertThat(newSmsClass).isEqualTo(changedSms)
    }

    @Test
    fun removeItem() {
        val smsClass = SmsClass(0, "sadadas", 1, arrayOf("09182121212"), "")
        Log.e("mmm2", smsRepositoryImp.smsList.toString())

        val newSmsClass = smsRepositoryImp.addItem(smsClass)
        Log.e("mmm", newSmsClass.toString())
        Log.e("mmm2", smsRepositoryImp.smsList.toString())
        smsRepositoryImp.removeItem(newSmsClass)
        Log.e("mmm3", smsRepositoryImp.smsList.toString())


        assertThat(smsRepositoryImp.smsList).doesNotContain(newSmsClass)
    }

    @Test
    fun getItems() {
    }
}