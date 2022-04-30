package com.task1.todo.Adapters

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.task1.todo.R
import com.task1.todo.TodoDataBase.TodoTable
import com.task1.todo.Update_Activity

class ListAdapter(var todos_List: MutableList<TodoTable>?) :
    RecyclerView.Adapter<ListAdapter.TodosListViewHolder>() {


    class TodosListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title_Of_Task: TextView = itemView.findViewById(R.id.title_Of_Task)
        var details_Of_Task: TextView = itemView.findViewById(R.id.details_Of_Task)
        var mark_As_Done: ImageView = itemView.findViewById(R.id.mark_As_Done)
        var delete: ImageView = itemView.findViewById(R.id.left_view)
        var task_Item: CardView = itemView.findViewById(R.id.drag_item)
        var view: View = itemView.findViewById(R.id.line)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosListViewHolder {

        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)

        return TodosListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodosListViewHolder, position: Int) {


        var items: TodoTable = todos_List!!.get(position)

        holder.title_Of_Task.setText(items.ttile)
        holder.details_Of_Task.setText(items.details)


        holder.title_Of_Task.setTextColor(Color.parseColor("#5D9CEC"))
        holder.details_Of_Task.setTextColor(Color.BLACK)
        holder.view.setBackgroundColor(Color.parseColor("#5D9CEC"))
        holder.mark_As_Done.setImageResource(R.drawable.ic_done)


        if (items.isDone == true) {

            holder.title_Of_Task.setTextColor(Color.GREEN)
            holder.details_Of_Task.setTextColor(Color.GREEN)
            holder.view.setBackgroundColor(Color.GREEN)
            holder.mark_As_Done.setImageResource(R.drawable.done)
        }

        if (onTodoSwiped != null)
            holder.delete.setOnClickListener {

                onTodoSwiped?.onTodoCliked(position)
            }

        if (onTaskClicked != null) {
            holder.task_Item.setOnClickListener {


                onTaskClicked?.onTodoCliked(position)


            }


        }

        if (onTodoMarkAsDone != null) {

            holder.mark_As_Done.setOnClickListener {


                onTodoMarkAsDone?.onTodoCliked(position)

                if (items.isDone == true) {

                    holder.title_Of_Task.setTextColor(Color.GREEN)
                    holder.details_Of_Task.setTextColor(Color.GREEN)
                    holder.view.setBackgroundColor(Color.GREEN)


                    holder.mark_As_Done.setImageResource(R.drawable.done)

                }
            }
        }

    }

    var onTodoSwiped: onTodoClickListener? = null
    var onTaskClicked: onTodoClickListener? = null
    var onTodoMarkAsDone: onTodoClickListener? = null

    interface onTodoClickListener {

        fun onTodoCliked(position: Int)
    }

    fun reload_Adapter(todos_List: MutableList<TodoTable>) {

        this.todos_List = todos_List
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return todos_List?.size ?: 0
    }
}