package com.mybeepertest.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.mybeepertest.MainActivity
import com.mybeepertest.services.CalculateWorkingDaysIntentService
import java.util.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var context: Context = application.baseContext

    var startDate: Date? = null
    var endDate: Date? = null
    var result: String? = null

    fun startService() {
        val intent = Intent(context, CalculateWorkingDaysIntentService::class.java)
        intent.putExtra(MainActivity.startDateString, startDate)
        intent.putExtra(MainActivity.endDateString, endDate)
        context.startService(intent)
    }
}