package com.task1.todo.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.task1.todo.Adapters.ListAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.task1.todo.*
import com.task1.todo.TodoDataBase.TodoTable
import java.util.*

class ListFragment : Fragment() {

    lateinit var todos_Recycler_View: RecyclerView
    var calendar: Calendar = Calendar.getInstance()

    companion object {
        lateinit var todosList: MutableList<TodoTable>
        var todo_Adapter: ListAdapter = ListAdapter(null)
        lateinit var calendarView: MaterialCalendarView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        get_All_Todos_From_DB_By_Day()
    }

    fun get_All_Todos_From_DB_By_Day() {

        todosList = MyDataBase.getInstance(requireContext()).getTodoDao()
            .getTodoByDay(calendar.clearTimeFromDate().time.time)

        Log.e("date time ", "" + calendar.clearTimeFromDate().time.time)

        todo_Adapter.reload_Adapter(todosList)
    }

    fun deleteTodoFromDB() {

        todo_Adapter.onTodoSwiped = object : ListAdapter.onTodoClickListener {
            override fun onTodoCliked(position: Int) {

                MyDataBase.getInstance(requireContext()).getTodoDao()
                    .deleteTodo(todosList[position])
                todosList.removeAt(position)
                todo_Adapter.reload_Adapter(todosList)
                Toast.makeText(requireContext(), "deleted successfully", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun moveToUpdateActivity() {

        todo_Adapter.onTaskClicked = object : ListAdapter.onTodoClickListener {
            override fun onTodoCliked(position: Int) {

                var intent: Intent = Intent(requireContext(), Update_Activity::class.java)

                intent.putExtra(Constansts.TASK_POSITION, position)

                startActivity(intent)

            }
        }
    }

    fun markAsDone() {

        todo_Adapter.onTodoMarkAsDone = object : ListAdapter.onTodoClickListener {
            override fun onTodoCliked(position: Int) {

                todosList[position].isDone = true
                MyDataBase.getInstance(requireContext()).getTodoDao()
                    .updateTodo(todosList[position])
                todo_Adapter.notifyItemChanged(position)
                todo_Adapter.reload_Adapter(todosList)
            }

        }
    }


    private fun initViews() {

        calendarView = requireView().findViewById(R.id.calendarView)
        todos_Recycler_View = requireView().findViewById(R.id.todos_Recycler_View)
        calendarView.selectedDate = CalendarDay.today()
        todos_Recycler_View.adapter = todo_Adapter

        calendarView.setOnDateChangedListener { widget, calendarDay, selected ->


            calendar.set(Calendar.DAY_OF_MONTH, calendarDay.day)
            calendar.set(Calendar.MONTH, calendarDay.month - 1)
            calendar.set(Calendar.YEAR, calendarDay.year)


            get_All_Todos_From_DB_By_Day()


        }
        deleteTodoFromDB()
        moveToUpdateActivity()
        markAsDone()

    }
}