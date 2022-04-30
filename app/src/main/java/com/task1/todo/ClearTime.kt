package com.task1.todo

import android.util.Log
import java.util.*


fun Calendar.clearTimeFromDate(): Calendar {


    this.clear(Calendar.HOUR)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.HOUR_OF_DAY)


    Log.e("clear time ", "" + this.time.time)


    return this
}
