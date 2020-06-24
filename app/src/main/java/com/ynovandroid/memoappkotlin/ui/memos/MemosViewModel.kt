package com.ynovandroid.memoappkotlin.ui.memos

import androidx.lifecycle.ViewModel
import com.ynovandroid.memoappkotlin.data.Memo
import com.ynovandroid.memoappkotlin.data.MemoRepository


class MemosViewModel(private val memoRepository: MemoRepository)
    : ViewModel() {

    fun getMemos() = memoRepository.getMemos()

    fun addMemo(memo: Memo) = memoRepository.addMemo(memo)

    fun removeMemo(memo: Memo) = memoRepository.removeMemo(memo)

    fun getNewMemoId(): Int{
        var memos = getMemos().value
        if (memos != null && memos.any()) {
            return memos.last().memoId + 1
        }
        return 1
    }

}