package com.task1.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.task1.todo.Dao.TodaDao
import com.task1.todo.TodoDataBase.TodoTable

@Database(entities = [TodoTable::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract fun getTodoDao(): TodaDao

    companion object {

        var myDataBase: MyDataBase? = null
        var NAME_OF_DATABASE: String = "Todo DataBase"

        fun getInstance(context: Context): MyDataBase {


            if (myDataBase == null) {

                myDataBase = Room.databaseBuilder(context, MyDataBase::class.java, NAME_OF_DATABASE)
                    .allowMainThreadQueries()
                    .build()
            }

            return myDataBase!!
        }
    }
}