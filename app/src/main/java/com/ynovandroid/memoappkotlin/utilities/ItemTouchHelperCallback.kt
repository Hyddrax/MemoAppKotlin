package com.ynovandroid.memoappkotlin.utilities

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ynovandroid.memoappkotlin.data.MemoAdapter

class ItemTouchHelperCallback(private val adapter: MemoAdapter?) : ItemTouchHelper.Callback() {
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlagsUpDown = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val dragFlagsLeftRight = ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlagsUpDown, dragFlagsLeftRight)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
        adapter!!.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        if ( direction == ItemTouchHelper.RIGHT){
            adapter!!.onItemDismiss(viewHolder, viewHolder.adapterPosition)
        }
    }

}