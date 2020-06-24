package com.ynovandroid.memoappkotlin.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Memo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDAO(): MemoDAO
}