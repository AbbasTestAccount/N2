package com.example.n2

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.netty.util.internal.logging.InternalLoggerFactory
import io.netty.util.internal.logging.JdkLoggerFactory
import org.koin.dsl.module
import org.koin.ktor.ext.Koin


const val PORT = 8080

class HttpService() : JobIntentService() {
    lateinit var database: SmsDao

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            InternalLoggerFactory.setDefaultFactory(JdkLoggerFactory.INSTANCE)
            embeddedServer(Netty, PORT) {
                install(ContentNegotiation) { gson {} }
                handleException()
                install(Koin) {
                    modules(
                        module {
                            single<SmsRepository> { SmsRepositoryImp(database, applicationContext) }
                            single { SmsService2() }
                        }
                    )
                }
                install(Routing) {
                    smsController()
                }
            }.start(wait = true)
        }.start()

        return START_STICKY
    }
    override fun onCreate() {
        super.onCreate()
        database = MyDatabase.getDatabase(this).smsDao

    }

    override fun onDestroy() {
        Log.e("Abbas", "onDestroy: ")
    }

    override fun onBind(intent: Intent): IBinder? = null
    override fun onHandleWork(intent: Intent) {
        TODO("Not yet implemented")
    }

}

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootCompletedReceiver", "starting service HttpService...")
            context.startService(Intent(context, HttpService::class.java))
        }
    }
}