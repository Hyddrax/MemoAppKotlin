package com.ynovandroid.memoappkotlin.data

import android.content.Context
import androidx.room.Room

class AppDatabaseHelper private constructor(context: Context){
    private val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "memos.db").allowMainThreadQueries().build()


    companion object {
        private var databaseHelper: AppDatabaseHelper? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            if (databaseHelper == null) {
                databaseHelper = AppDatabaseHelper(context.applicationContext)
            }
            return databaseHelper!!.database
        }
    }

}