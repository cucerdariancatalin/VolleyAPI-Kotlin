package com.example.assig5.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assig5.R
import com.example.assig5.model.ToDo
import kotlinx.android.synthetic.main.data_item.view.*

class TodoAdapter(var activity: Activity,var data:ArrayList<ToDo>) : RecyclerView.Adapter<TodoAdapter.MyViewHolder> (){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox = itemView.checkBox
        val CheckText = itemView.txt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.data_item, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TodoAdapter.MyViewHolder, position: Int) {
        holder.checkBox.isChecked = data[position].status
        holder.CheckText.text = data[position].title
    }


}