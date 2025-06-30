package com.example.javaLearningApp.ProgramAndSyntaxFragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.javaLearningApp.R

class NestedAdapter(private val mList: List<Pair<String, Any>>) : RecyclerView.Adapter<NestedAdapter.NestedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nested_item_program, parent, false)
        return NestedViewHolder(view)
    }

    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        val context = holder.itemView.context
        val nestedListItem = mList[position]

        holder.mTv.text = nestedListItem.first
        holder.layout_show_prog.setOnClickListener {
            // Create a new Intent
            val intent = Intent(holder.itemView.context, ShowProgramSyntaxActivity::class.java)

            // Add extra values to the intent
            val programText = mList[position].second
            //intent.putExtra("programText", programText)
           // Start the activity
            holder.itemView.context.startActivity(intent)
        }


    }


    override fun getItemCount(): Int {
        return mList.size
    }

    inner class NestedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTv: TextView = itemView.findViewById(R.id.program_tv)
        val layout_show_prog: RelativeLayout = itemView.findViewById(R.id.layout_show_prog)
        //val program: TextView = itemView.findViewById(R.id.program)
    }
}
