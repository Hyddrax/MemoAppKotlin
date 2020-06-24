package com.ynovandroid.memoappkotlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
data class Memo(
    @PrimaryKey
    val memoId: Int,
    val title: String,
    var content: String
)