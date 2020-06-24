package com.ynovandroid.memoappkotlin.ui.memos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.ynovandroid.memoappkotlin.R
import com.ynovandroid.memoappkotlin.data.Memo

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment : Fragment() {
    lateinit var memo: Memo
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_content, container, false)
        val gson = Gson()
        memo = gson.fromJson(arguments!!.getString("memo"), Memo::class.java)
        val textView = view.findViewById<TextView>(R.id.memo_content)
        textView.text = memo.content
        return view
    }
}