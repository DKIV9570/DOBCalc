package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var tvSelectedDate : TextView? = null
    var ageInMin : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        ageInMin = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
        btnDatePicker.setOnClickListener{
            this.clickDatePicker()
        }
    }
    fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                view, year, month, dayofMonth ->

                val selectedDate = "$dayofMonth/${month+1}/$year"
                tvSelectedDate?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate.time/ 60000

                val ageInMinutes = currentDateInMinutes - selectedDateInMinutes

                ageInMin?.setText(ageInMinutes.toString())


                Toast.makeText(this,"$theDate!"
                    ,Toast.LENGTH_LONG).show()
                                              },
            year, month, day
        ).show()


    }
}