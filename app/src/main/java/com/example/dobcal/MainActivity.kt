package com.example.dobcal

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btDatePicker : Button = findViewById(R.id.btDatePicker)
        selectedDate = findViewById(R.id.selectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {view, year, month, dayOfMonth ->
                Toast.makeText(this,
                    "A date is selected", Toast.LENGTH_SHORT).show()

                val date = "$dayOfMonth/${month+1}/$year"
                selectedDate?.text = date

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(date)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time/ 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val dateDiference = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text = dateDiference.toString()
                    }

                }

            },

            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}