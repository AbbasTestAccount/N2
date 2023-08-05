package com.example.n2.room

import androidx.test.filters.SmallTest
import com.example.n2.getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.n2.repository.room.MyDatabase
import com.example.n2.repository.room.SmsClass
import com.example.n2.repository.room.SmsDao

@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4ClassRunner::class)
class SmsDaoTest {
    private lateinit var database: MyDatabase
    private lateinit var dao: SmsDao


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = MyDatabase.getDatabase(getApplicationContext())
        dao = database.smsDao
    }

    @After
    fun atTheEnd() {
        database.close()
    }

    @Test
    fun addSmsItem() = runBlocking {
        val smsItem = SmsClass(1,"saas", 1, arrayOf("09123211321"), "")
        dao.addSms(smsItem)

        val allSmsItems = dao.getAllSms().getOrAwaitValue()

        assertThat(allSmsItems).contains(smsItem)
    }

    @Test
    fun deleteSmsItem() = runBlocking {
        val smsItem = SmsClass(1,"saas", 1, arrayOf("09123211321"), "")
        dao.deleteSms(smsItem)

        val allSmsItems = dao.getAllSms().getOrAwaitValue()

        assertThat(allSmsItems).doesNotContain(smsItem)
    }



}