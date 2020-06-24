package com.ynovandroid.memoappkotlin.ui.memos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ynovandroid.memoappkotlin.R
import com.ynovandroid.memoappkotlin.data.Memo

class ContentActivity : AppCompatActivity() {
    var memo: Memo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        val gson = Gson()
        memo = gson.fromJson(intent.getStringExtra("memo"), Memo::class.java)
        val fragment = ContentFragment()
        val bundle = Bundle()
        bundle.putString("memo", gson.toJson(memo))
        fragment.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.conteneur_fragment, fragment, "memoContent")
        fragmentTransaction.commit()
    }
}