package com.example.recycleview.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.R
import com.example.recycleview.data.DummyData
import java.util.Timer
import java.util.TimerTask

class MyRecyclerViewAdapter(
    private val dataList: List<DummyData>,
    private val onItemShownCallback: (Int) -> Unit,
    private val onClickAddListen: (Int) -> Unit
)
    : RecyclerView.Adapter<MyViewHolder>()
{

    private var timer: Timer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description

        holder.buttonSub.text = if (currentItem.listening) "Un-Listen" else "Listen"
        holder.buttonSub.setOnClickListener { onClickAddListen(position) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)

        if (dataList[holder.adapterPosition].listening) {
            timer?.cancel()
            timer = Timer().apply {
                schedule(object : TimerTask() {
                    override fun run() {
                        onItemShownCallback(holder.adapterPosition)
                    }
                }, 2000)
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (dataList[holder.adapterPosition].listening){
            timer?.cancel()
            timer = null
        }
    }

}
