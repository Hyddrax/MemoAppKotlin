package com.ynovandroid.memoappkotlin.data


class MemoRepository private constructor(private val memoDao: MemoDAO){

    fun addMemo(memo: Memo) {
        memoDao.addMemo(memo)
    }

    fun getMemos() = memoDao.getMemos()

    fun removeMemo(memo: Memo) {
        memoDao.removeMemo(memo)
    }

    companion object {
        @Volatile private var instance: MemoRepository? = null

        fun getInstance(memoDao: MemoDAO) =
                instance ?: synchronized(this) {
                    instance ?: MemoRepository(memoDao).also { instance = it }
                }
    }
}