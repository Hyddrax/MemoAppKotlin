package com.ynovandroid.memoappkotlin.ui.memos

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynovandroid.memoappkotlin.R
import com.ynovandroid.memoappkotlin.data.Memo
import com.ynovandroid.memoappkotlin.data.MemoAdapter
import com.ynovandroid.memoappkotlin.utilities.ItemTouchHelperCallback
import com.ynovandroid.memoappkotlin.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var memoAdapter: MemoAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private var itemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initializeUi()
    }

    private fun initRecyclerView(){
        recyclerView = findViewById(R.id.list_memo)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        memoAdapter = MemoAdapter( this)
        recyclerView.adapter = memoAdapter
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(memoAdapter))
        itemTouchHelper!!.attachToRecyclerView(recyclerView)
    }

    private fun initializeUi() {
        val factory = InjectorUtils.provideMemosViewModelFactory(this)
        val viewModel = ViewModelProviders.of(this, factory)
                .get(MemosViewModel::class.java)

        add_memo_btn.setOnClickListener {
            val editText = findViewById<EditText>(R.id.add_memo)
            if (!editText.text.toString().trim { it <= ' ' }.isEmpty()) {
                var nMemo = Memo(viewModel.getNewMemoId(),editText.text.toString(), "")
                viewModel.addMemo(nMemo)
                memoAdapter!!.notifyItemInserted(viewModel.getMemos().value!!.size-1)
                recyclerView.smoothScrollToPosition(0)
                editText.setText("")
            }
        }
    }
}