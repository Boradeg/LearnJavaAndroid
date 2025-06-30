package com.example.javaLearningApp.TutorialFragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.javaLearningApp.R

class NestedAdapterTutorial(private val mList: List<String>) : RecyclerView.Adapter<NestedAdapterTutorial.NestedViewHolder1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder1 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nested_item_tutorial2, parent, false)
        return NestedViewHolder1(view)
    }

    override fun onBindViewHolder(holder: NestedViewHolder1, position: Int) {
        holder.mTv.text = mList[position]
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class NestedViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTv: TextView = itemView.findViewById(R.id.nested_item_text_tutorial)
    }
}
