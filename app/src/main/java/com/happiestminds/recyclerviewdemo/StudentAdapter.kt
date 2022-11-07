package com.happiestminds.recyclerviewdemo

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class studentAdapter(val dataSet: MutableList<Student,>,val action : (Student,Int) -> Unit) :
    RecyclerView.Adapter<studentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val namTextView = itemView.findViewById<TextView>(R.id.nameT)
        val marksTextView = itemView.findViewById<TextView>(R.id.marksT)
        val rollTextView = itemView.findViewById<TextView>(R.id.rollT)

    }

    //called by recyclerView when views to be created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        //inflate layout and create the viewHolder
       val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        Log.d("StudentAdapter","OnCreatedViewHolder called")
        return StudentViewHolder(view)
    }

//called by RecyclerView when data to be bound by views
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    Log.d("StudentAdapter", "OnBindViewHolder called $position")
    val std = dataSet[position]
    holder.namTextView.text = std.name
    holder.rollTextView.text = "Roll No : ${std.rollNo}"
    holder.marksTextView.text = "Marks: ${std.marks}"

    //in adpter we are setting a onclicklistner
    holder.itemView.setOnClickListener{
        action(std,holder.adapterPosition)

    }

      val cardv = holder.itemView as CardView
    when (std.marks) {
        in 75..100 -> cardv.setCardBackgroundColor(Color.GREEN)

        in 65..75 -> cardv.setCardBackgroundColor(Color.BLUE)
        in 55..65 -> cardv.setCardBackgroundColor(Color.WHITE)
        in 35..55 -> cardv.setCardBackgroundColor(Color.YELLOW)

        else -> cardv.setCardBackgroundColor(Color.DKGRAY)
    }
}

    override fun getItemCount(): Int {
        return dataSet.size
    }

}