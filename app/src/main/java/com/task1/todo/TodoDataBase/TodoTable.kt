package com.task1.todo.TodoDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TodoTable(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int? = null,

    @ColumnInfo
    var ttile: String? = null,

    @ColumnInfo
    var details: String? = null,

    @ColumnInfo
    var date: Long? = null,

    @ColumnInfo
    var isDone: Boolean? = false
)
