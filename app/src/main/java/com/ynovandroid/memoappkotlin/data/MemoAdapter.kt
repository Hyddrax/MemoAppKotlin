package com.ynovandroid.memoappkotlin.data

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.ynovandroid.memoappkotlin.R
import com.ynovandroid.memoappkotlin.ui.memos.ContentFragment
import com.ynovandroid.memoappkotlin.data.MemoAdapter.MemoViewHolder
import com.ynovandroid.memoappkotlin.ui.memos.ContentActivity
import com.ynovandroid.memoappkotlin.ui.memos.MemosViewModel
import com.ynovandroid.memoappkotlin.utilities.InjectorUtils

import cz.msebera.android.httpclient.Header
import java.util.*

@Suppress("NAME_SHADOWING")
class MemoAdapter(private val activity: AppCompatActivity) : RecyclerView.Adapter<MemoViewHolder>() {
    private var viewModel: MemosViewModel
    private var memoList: MutableList<Memo>

    init {
        this.memoList = mutableListOf<Memo>()
        val factory = InjectorUtils.provideMemosViewModelFactory(activity)
        viewModel = ViewModelProviders.of(activity, factory)
            .get(MemosViewModel::class.java)

        //memoList update itself when memosList from the ViewModel hasChange
        viewModel.getMemos().observe(activity, Observer { memos ->
            this.memoList = memos as MutableList<Memo>
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val viewCourse = LayoutInflater.from(parent.context).inflate(R.layout.memo_item_list, parent, false)
        return MemoViewHolder(viewCourse)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.textViewTitleMemo.text = memoList!![position]!!.title
    }

    override fun getItemCount(): Int {
        if (memoList != null) {
            return memoList.size
        }
        return 0
    }

    fun onItemMove(positionDebut: Int, positionFin: Int): Boolean {
        Collections.swap(memoList, positionDebut, positionFin)
        notifyItemMoved(positionDebut, positionFin)
        return true
    }

    fun onItemDismiss(viewHolder: ViewHolder, position: Int) {
        if (position > -1) {
            viewModel.removeMemo(memoList[position])
            notifyItemRemoved(position)
        }
    }

    companion object {
        private const val TAG = "MemoViewHolder"
    }

    inner class MemoViewHolder(itemView: View) : ViewHolder(itemView) {
        var textViewTitleMemo: TextView = itemView.findViewById(R.id.title_memo)

        inner class RetourWS {
            var form: Form? = null

            inner class Form {
                var memo: String? = null
            }
        }

        init {
            textViewTitleMemo.setOnClickListener {
                val gSon = Gson()
                val preferences = PreferenceManager.getDefaultSharedPreferences(itemView.context)
                val editor = preferences.edit()
                editor.putInt("lastPos", adapterPosition)
                editor.apply()
                val memo = memoList!![adapterPosition]
                val client = AsyncHttpClient()
                val requestParams = RequestParams()
                requestParams.put("memo", gSon.toJson(memo))
                client.post("http://httpbin.org/post", requestParams, object : AsyncHttpResponseHandler() {
                    override fun onSuccess(statusCode: Int, headers: Array<Header>, response: ByteArray) {
                        val retour = String(response)
                        val gSon = Gson()
                        val retourWS = gSon.fromJson(retour, RetourWS::class.java)
                        val tmpMemo = gSon.fromJson(retourWS.form!!.memo, Memo::class.java)
                        Log.d(TAG, "onSuccess: " + adapterPosition + " " + tmpMemo.title + " " + tmpMemo.content)
                        Log.i(TAG, gSon.toJson(retourWS))
                    }

                    override fun onFailure(statusCode: Int, headers: Array<Header>, errorResponse: ByteArray, e: Throwable) {
                        Log.e(TAG, e.toString())
                    }
                })
                memo!!.content = "Ceci un Content Generique ! "
                memo.content = "Ceci un Content Generique !Cecntee  !Ceci un Content  !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique ! "
                memo.content += "Ceci un Content Generique !Cecntee  !Ceci un Content  !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique ! "
                if (activity.findViewById<View?>(R.id.conteneurMain_fragment) == null) {
                    val intent = Intent(itemView.context, ContentActivity::class.java)
                    intent.putExtra("memo", gSon.toJson(memo))
                    itemView.context.startActivity(intent)
                } else {
                    val fragment = ContentFragment()
                    val bundle = Bundle()
                    bundle.putString("memo", gSon.toJson(memo))
                    fragment.arguments = bundle
                    val fragmentManager = activity.supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.conteneurMain_fragment, fragment, "memoContent")
                    fragmentTransaction.commit()
                }
            }

        }

    }

}
