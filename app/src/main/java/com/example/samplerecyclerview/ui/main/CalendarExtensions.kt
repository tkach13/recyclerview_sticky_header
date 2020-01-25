package com.example.samplerecyclerview.ui.main

import java.util.*


fun getYear(date: Date?):Int?{
    if (date != null ){
        val calendar = Calendar.getInstance()

        calendar.time = date

        return calendar.get(Calendar.YEAR)

    }

    return null
}