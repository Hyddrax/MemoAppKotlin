package com.ynovandroid.memoappkotlin.utilities

import android.content.Context
import com.ynovandroid.memoappkotlin.data.AppDatabaseHelper
import com.ynovandroid.memoappkotlin.data.MemoRepository
import com.ynovandroid.memoappkotlin.ui.memos.MemosViewModelFactory


object InjectorUtils {

    fun provideMemosViewModelFactory(context: Context): MemosViewModelFactory {
        val quoteRepository = MemoRepository.getInstance(AppDatabaseHelper.getDatabase(context).memoDAO())
        return MemosViewModelFactory(quoteRepository)
    }
}