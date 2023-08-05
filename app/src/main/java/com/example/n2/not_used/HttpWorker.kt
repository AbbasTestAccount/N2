package com.example.n2.not_used

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.n2.repository.room.MyDatabase

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