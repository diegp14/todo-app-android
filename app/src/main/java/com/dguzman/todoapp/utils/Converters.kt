package com.dguzman.todoapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

class Converters {



    companion object {
        @SuppressLint("SimpleDateFormat")
        fun formatDate(time: Long): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val date = Date(time)
            return formatter.format(date)
        }
    }
}