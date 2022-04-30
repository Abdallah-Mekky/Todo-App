package com.task1.todo

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputLayout
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.task1.todo.Fragments.ListFragment
import java.util.*


class Update_Activity : AppCompatActivity() {


    lateinit var update_Title_Layout: TextInputLayout
    lateinit var update_Details_Layout: TextInputLayout
    lateinit var update_Title_Text: EditText
    lateinit var update_Details_Text: EditText
    lateinit var update_Date: TextView
    lateinit var save_Changes: TextView
    lateinit var markAsDone: MaterialCheckBox
    var calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        initViews()
        update_Date.setOnClickListener {

            showDataPicker()
        }


        save_Changes.setOnClickListener {

            if (vaidtadeTextInputsLayout()) {

                var new_Title: String = update_Title_Layout.editText?.text.toString()
                var new_Details: String = update_Details_Layout.editText?.text.toString()
                var date: Long = calendar.clearTimeFromDate().time.time
                var markAsDone: Boolean = markAsDone.isChecked



                update_Todo_Informtion(new_Title, new_Details, date, markAsDone)
                finish()
                ListFragment.calendarView.selectedDate = CalendarDay.today()
            }
        }
    }


    private fun update_Todo_Informtion(
        newTitle: String,
        newDetails: String,
        date: Long,
        markAsDone: Boolean
    ) {

        var position_Of_Todo: Int = intent.getIntExtra(Constansts.TASK_POSITION, 0)

        //Change UI Information
        ListFragment.todosList[position_Of_Todo].ttile = newTitle
        ListFragment.todosList[position_Of_Todo].details = newDetails
        ListFragment.todosList[position_Of_Todo].date = date
        ListFragment.todosList[position_Of_Todo].isDone = markAsDone

        //Change DataBase Infomtion
        MyDataBase.getInstance(this).getTodoDao()
            .updateTodo(ListFragment.todosList[position_Of_Todo])

        ListFragment.todo_Adapter.reload_Adapter(ListFragment.todosList)

        Toast.makeText(this, "updated successfully", Toast.LENGTH_LONG).show()
    }

    private fun showDataPicker() {

        var datePickerDialog = DatePickerDialog(
            this,
            { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                update_Date.setText("" + year + "/" + (month + 1) + "/" + dayOfMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()

    }

    fun vaidtadeTextInputsLayout(): Boolean {

        var isVaild: Boolean = true

        if (update_Title_Layout.editText?.text.toString().isBlank()) {

            update_Title_Layout.error = "Please Enter Title"
            isVaild = false
        } else {

            update_Title_Layout.error = null
        }

        if (update_Details_Layout.editText?.text.toString().isBlank()) {

            update_Details_Layout.error = "Please Enter Details"
            isVaild = false
        } else {

            update_Details_Layout.error = null
        }

        return isVaild
    }


    private fun initViews() {

        update_Title_Layout = findViewById(R.id.update_Title_Layout)
        update_Details_Layout = findViewById(R.id.update_Details_Layout)
        update_Title_Text = findViewById(R.id.update_Title)
        update_Details_Text = findViewById(R.id.update_details)
        update_Date = findViewById(R.id.update_Date)
        save_Changes = findViewById(R.id.save_Changes)
        markAsDone = findViewById(R.id.markAsDone)


        update_Date.setText(
            "" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + " /" +
                    calendar.get(Calendar.YEAR)
        )
    }


}