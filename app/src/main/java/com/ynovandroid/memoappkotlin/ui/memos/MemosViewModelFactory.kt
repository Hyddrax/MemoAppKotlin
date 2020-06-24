package com.ynovandroid.memoappkotlin.ui.memos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ynovandroid.memoappkotlin.data.MemoRepository


class MemosViewModelFactory(private val memoRepository: MemoRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemosViewModel(memoRepository) as T
    }
}