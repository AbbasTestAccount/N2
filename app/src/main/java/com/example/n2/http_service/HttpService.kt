package com.example.n2.http_service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import com.example.n2.repository.SmsRepository
import com.example.n2.repository.SmsRepositoryImp
import com.example.n2.repository.room.MyDatabase
import com.example.n2.repository.room.SmsDao
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.netty.util.internal.logging.InternalLoggerFactory
import io.netty.util.internal.logging.JdkLoggerFactory
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.ktor.ext.Koin


const val PORT = 8080

class HttpService() : JobIntentService() {
    lateinit var database: SmsDao

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        makeForeground(applicationContext)

        Log.e("TAG", "onStartCommand: ")

        Toast.makeText(this, "tessssssssssssst", Toast.LENGTH_SHORT).show()
        Thread {
            InternalLoggerFactory.setDefaultFactory(JdkLoggerFactory.INSTANCE)
            embeddedServer(Netty, PORT) {
                install(ContentNegotiation) { gson {} }
                handleException()
                stopKoin()
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



    private fun makeForeground(applicationContext: Context) {
        startForeground(10001, makeNotif(applicationContext))
    }

    private fun makeNotif(applicationContext: Context): Notification {

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = 1
        val notificationChannelId = "default_notification_channel_id"


        // Create notification channel for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                notificationChannelId,
                "Default Notification",
                NotificationManager.IMPORTANCE_LOW
            )

            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notification = NotificationCompat.Builder(applicationContext, notificationChannelId)
            .setContentTitle("test")
        return notification.build()

    }

    override fun onCreate() {
        super.onCreate()
        database = MyDatabase.getDatabase(this).smsDao

    }

    override fun onDestroy() {
        Log.e("Abbas", "onDestroy: ")

//
//        val broadcastIntent = Intent(this, RestarterBroadcastReceiver::class.java)
//
//        sendBroadcast(broadcastIntent)
//        Log.e("Abbas2", "onDestroy: ")


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