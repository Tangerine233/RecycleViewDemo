package com.example.recycleview.recyclerView

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.R

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    val buttonSub: Button = itemView.findViewById(R.id.buttonSub)
}