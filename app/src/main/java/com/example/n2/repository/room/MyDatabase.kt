package com.example.n2.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.n2.not_used.Converters

@Database(entities = [SmsClass::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract val smsDao: SmsDao

    companion object {

        private var dataBase: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase {
            if (dataBase == null) {
                dataBase = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myDatabase.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dataBase!!
        }

    }

}