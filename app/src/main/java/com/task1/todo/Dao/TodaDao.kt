package com.task1.todo.Dao

import androidx.room.*
import com.task1.todo.TodoDataBase.TodoTable
import java.util.*


@Dao
interface TodaDao {

    @Insert
    fun insertTodo(todo: TodoTable)

    @Update
    fun updateTodo(todo: TodoTable)

    @Delete
    fun deleteTodo(todo: TodoTable)


    @Query("select * from TodoTable")
    fun getAllTodos(): MutableList<TodoTable>


    @Query("select * from TodoTable where date = :date")
    fun getTodoByDay(date: Long): MutableList<TodoTable>
}