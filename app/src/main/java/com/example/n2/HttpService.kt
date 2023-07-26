package com.example.n2

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
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

class HttpService() : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
    override fun onCreate() {
        super.onCreate()
        val database = MyDatabase.getDatabase(this).smsDao

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
    }

    override fun onBind(intent: Intent): IBinder? = null
}

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootCompletedReceiver", "starting service HttpService...")
            context.startService(Intent(context, HttpService::class.java))
        }
    }
}