package com.mybeepertest

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.mybeepertest.entities.Holidays
import com.mybeepertest.viewmodel.MainActivityViewModel
import ikartiks.expensetracker.dao.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Suppress("DEPRECATION")
@SuppressLint("SetTextI18n")
class MainActivity : BaseActivity(), DatePickerDialog.OnDateSetListener, View.OnClickListener {

    companion object {
        const val startDateString = "startDate"
        const val endDateString = "endDate"
        const val resultReceiver = "resultReceiver"
        const val result = "result"
    }

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {

        if (view.tag == startDateString) {
            mainActivityViewModel.startDate = Date(year, month, dayOfMonth)
            startDate.text =
                "${mainActivityViewModel.startDate?.date}/${mainActivityViewModel.startDate?.month}/${mainActivityViewModel.startDate?.year}"
        } else if (view.tag == endDateString) {
            mainActivityViewModel.endDate = Date(year, month, dayOfMonth)
            endDate.text =
                "${mainActivityViewModel.endDate?.date}/${mainActivityViewModel.endDate?.month}/${mainActivityViewModel.endDate?.year}"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.startDate?.let {
            startDate.text =
                "${mainActivityViewModel.startDate?.date}/${mainActivityViewModel.startDate?.month}/${mainActivityViewModel.startDate?.year}"
        }
        mainActivityViewModel.endDate?.let {
            endDate.text =
                "${mainActivityViewModel.endDate?.date}/${mainActivityViewModel.endDate?.month}/${mainActivityViewModel.endDate?.year}"
        }
        mainActivityViewModel.result?.let {
            result.text = mainActivityViewModel.result
            result.visibility = View.VISIBLE
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, IntentFilter(resultReceiver))
        startDate.setOnClickListener(this)
        endDate.setOnClickListener(this)
        calculate.setOnClickListener(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (!getBoolean(getString(R.string.app_initialised), false)) {

            Thread(Runnable {
                val appDatabase = AppDatabase.getInstance(this)

                var holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 0, 1)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 0, 28)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 3, 19)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = false
                holiday.date = Date(2019, 3, 20)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 3, 21)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 3, 22)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 3, 25)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 5, 10)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 9, 7)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 11, 25)
                appDatabase.appDao().addHoliday(holiday)

                holiday = Holidays()
                holiday.canBeCarryforWorded = true
                holiday.date = Date(2019, 11, 26)
                appDatabase.appDao().addHoliday(holiday)
            }).start()


            putBoolean(getString(R.string.app_initialised), true)
        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.startDate -> {

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                // Create a new instance of DatePickerDialog and return it
                val datePickerDialog = DatePickerDialog(this, this, year, month, day)
                datePickerDialog.datePicker.tag = startDateString
                datePickerDialog.show()
            }

            R.id.endDate -> {

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                // Create a new instance of DatePickerDialog and return it
                val datePickerDialog = DatePickerDialog(this, this, year, month, day)
                datePickerDialog.datePicker.tag = endDateString
                datePickerDialog.show()
            }

            R.id.calculate -> {
                if (mainActivityViewModel.startDate != null && mainActivityViewModel.endDate != null) {
                    mainActivityViewModel.startService()
                } else {
                    showCustomMessage(getString(R.string.error_msg_dates))
                }
            }
        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
        super.onDestroy()
    }

    private val mMessageReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val message = intent?.extras?.get(MainActivity.result)
            val output =
                "Number of working days between ${mainActivityViewModel.startDate?.date}/${mainActivityViewModel.startDate?.month}/${mainActivityViewModel.startDate?.year}" +
                        " and ${mainActivityViewModel.endDate?.date}/${mainActivityViewModel.endDate?.month}/${mainActivityViewModel.endDate?.year}" +
                        " is $message "
            result.text = output
            result.visibility = View.VISIBLE
            mainActivityViewModel.result = output
        }
    }

}
