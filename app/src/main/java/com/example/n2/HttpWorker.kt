package com.example.n2

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
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

class HttpWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val database = MyDatabase.getDatabase(context).smsDao

//        Thread {
//            InternalLoggerFactory.setDefaultFactory(JdkLoggerFactory.INSTANCE)
//            embeddedServer(Netty, PORT) {
//                install(ContentNegotiation) { gson {} }
//                handleException()
//                install(Koin) {
//                    modules(
//                        module {
//                            single<SmsRepository> { SmsRepositoryImp(database, applicationContext) }
//                            single { SmsService2() }
//                        }
//                    )
//                }
//                install(Routing) {
//                    smsController()
//                }
//            }.start(wait = true)
//        }.start()


        return Result.success()
    }
}