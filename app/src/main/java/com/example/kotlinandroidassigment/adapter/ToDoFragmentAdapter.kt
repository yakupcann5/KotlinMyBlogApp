package com.example.kotlinandroidassigment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinandroidassigment.R
import com.example.kotlinandroidassigment.model.Post
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_todo_row.view.*

class ToDoFragmentAdapter (private val posts : List<Post?>, context: Context):
    RecyclerView.Adapter<ToDoFragmentAdapter.ToDoViewHolder>(){
    class ToDoViewHolder (view: View):RecyclerView.ViewHolder(view){
        val id : TextView = view.findViewById(R.id.textViewId)
        private val title : TextView = view.findViewById(R.id.textViewTitle)
        private val complate : TextView = view.findViewById(R.id.textViewComplate)

        fun bindItems(post : Post?){
            id.text = post!!.id
            title.text = post.title
            complate.text = post.completed
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_todo_row, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder:ToDoViewHolder, position: Int) {
        holder.bindItems(posts.get(position))
        holder.itemView.textViewId.text = posts.get(position)?.id
        holder.itemView.textViewTitle.text = posts.get(position)?.title
        holder.itemView.textViewComplate.text = posts.get(position)?.completed
        holder.itemView.textViewId.setOnClickListener {
            Snackbar.make(it,"Complated "+holder.itemView.textViewComplate.text,Snackbar.LENGTH_LONG)
                .setAction("Action",null)
                .show()
        }
    }
    override fun getItemCount(): Int {
        return posts.size
    }
}