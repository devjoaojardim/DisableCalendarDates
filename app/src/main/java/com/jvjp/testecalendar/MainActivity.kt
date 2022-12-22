package com.jvjp.testecalendar


import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primecalendar.common.CalendarFactory
import com.aminography.primecalendar.common.CalendarType
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var button: Button
    private var dpd: DatePickerDialog? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data = Calendar.getInstance().time

        val today = CivilCalendar()

        getMinDateCalendar(CalendarType.CIVIL)


        //  val calendara = Calendar.getInstance()


        val callback = RangeDaysPickCallback { startDay, endDay ->

            longToast("From: ${startDay.longDateString}\nTo: ${endDay.longDateString}")

        }


        val datesCheck = ArrayList<String>()
        datesCheck.add("23/12/2022")
        datesCheck.add("27/12/2022")

        val dateCheckIn = 23
        val dateCheckOUT = 27
        val datesArray = ArrayList<Int>()


        val formatter1 = SimpleDateFormat("dd/MM/yyyy")

        data = formatter1.parse(datesCheck[0])

        val objectDate = DateMy(
            "26/12/2022"
        )

        val objectDate1 = DateMy(
            "27/12/2022"
        )
        val objectDate2 = DateMy(
            "28/12/2022"
        )
        val objectDate3 = DateMy(
            "02/01/2023"
        )

        val objectDate4 = DateMy(
            "03/01/2023"
        )
        val objectDate5 = DateMy(
            "17/01/2023"
        )
        val objectDate6 = DateMy(
            "18/01/2023"
        )
        val objectDate7 = DateMy(
            "19/01/2023"
        )
        val objectDate8 = DateMy(
            "20/01/2023"
        )
        val objectDate9 = DateMy(
            "21/01/2023"
        )
        val objectDate10 = DateMy(
            "22/01/2023"
        )


        val datasList = ArrayList<DateMy>()
        datasList.add(objectDate)
        datasList.add(objectDate1)
        datasList.add(objectDate2)
        datasList.add(objectDate3)
        datasList.add(objectDate4)
        datasList.add(objectDate6)
        datasList.add(objectDate7)
        datasList.add(objectDate8)
        datasList.add(objectDate9)
        datasList.add(objectDate10)


        val datePicker =
            PrimeDatePicker
                .bottomSheetWith(today)
                .pickRangeDays(callback = callback)
                .disabledDays(disabledDays = getDaysDisable(datasList))
                .minPossibleDate(today)
                .build()

        button = findViewById(R.id.button)


        button.setOnClickListener {

            datePicker.show(supportFragmentManager, "asdad")
        }

    }

    private fun longToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()



    private fun getMinDateCalendar(calendarType: CalendarType): PrimeCalendar? {
        val minDateCalendar: PrimeCalendar?
        minDateCalendar = CalendarFactory.newInstance(calendarType)
        minDateCalendar[Calendar.MONTH] -= 5

        return minDateCalendar
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDaysDisable(day: List<DateMy>): List<PrimeCalendar> {

        val listDays = ArrayList<PrimeCalendar>()

        for (i in 1..day.size) {
            for (day1 in day) {
                val disableDays: PrimeCalendar?
                disableDays = CalendarFactory.newInstance(CalendarType.CIVIL)


                val formatador = SimpleDateFormat("dd/MM/yyyy")

                disableDays.date = LocalDate.parse(day1.data, DateTimeFormatter.ofPattern("dd/MM/yyyy")).dayOfMonth
                disableDays.month = LocalDate.parse(day1.data, DateTimeFormatter.ofPattern("dd/MM/yyyy")).monthValue - 1
                disableDays.year = LocalDate.parse(day1.data, DateTimeFormatter.ofPattern("dd/MM/yyyy")).year

                println(disableDays.month)
                listDays.add(disableDays)
                println(day)
            }

        }

        return listDays
    }


    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        TODO("Not yet implemented")
    }


}