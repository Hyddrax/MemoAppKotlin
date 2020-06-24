package com.ynovandroid.memoappkotlin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.ynovandroid.memoappkotlin.data.Memo

@Dao
abstract class MemoDAO {
    private var memoList = mutableListOf<Memo>()
    private val memos = MutableLiveData<List<Memo>>()

    init {
        memos.value = memoList
    }

    fun addMemo(memo: Memo) {
        insert(memo)
        memoList.add(memo)
        memos.value = memoList
    }

    fun updateMemo(memo: Memo) {
        update(memo)
        memoList[memoList.indexOf(memo)] = memo
        memos.value = memoList
    }

    fun removeMemo(memo: Memo) {
        delete(memo)
        memoList.remove(memo)
        memos.value = memoList
    }


    fun getMemos(): LiveData<List<Memo>>{
        memoList = listeMemos()
        memos.value = memoList
        return memos
    }

    @Query("SELECT * FROM memos")
    abstract fun listeMemos(): MutableList<Memo>

    @Insert
    abstract fun insert(vararg memos: Memo)

    @Update
    abstract fun update(vararg memos: Memo)

    @Delete
    abstract fun delete(vararg memos: Memo)
}