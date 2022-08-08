package com.project.todoslist.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.todoslist.R
import com.project.todoslist.databinding.TodosListItemsBinding
import com.project.todoslist.model.TodosItems

class TodosViewAdapter(private val todoList: List<TodosItems>) :
    RecyclerView.Adapter<TodosViewAdapter.TodosViewHolder>() {
    inner class TodosViewHolder(private val binding: TodosListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: TodosItems) {
            binding.apply {
                txtTitle.text = item.title.take(12).plus("...").uppercase()
                when (item.completed) {
                    true -> checkBox.isChecked = true
                    else -> checkBox.isChecked = false
                }

                checkBox.setBackgroundColor(Color.parseColor("#EAECEE"))

                Glide.with(img)
                    .load(R.drawable.todos)
                    .placeholder(R.drawable.loading)
                    .into(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        return TodosViewHolder(
            TodosListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodosViewAdapter.TodosViewHolder, position: Int) {
        holder.onBind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun txtSubstring(str: String, start: Int, end: Int): String {
        return "${str.substring(start, end)}..."
    }
}