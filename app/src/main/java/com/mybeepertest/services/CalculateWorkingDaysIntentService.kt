package com.mybeepertest.services

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.mybeepertest.MainActivity
import com.mybeepertest.model.CalculateDateModel
import java.util.*


class CalculateWorkingDaysIntentService : IntentService("CalculateWorkingDaysIntentService") {

    override fun onHandleIntent(intent: Intent?) {

        val startDate = intent?.getSerializableExtra(MainActivity.startDateString) as Date
        val endDate = intent?.getSerializableExtra(MainActivity.endDateString) as Date

        val calculateDateModel = CalculateDateModel(this)
        val result = calculateDateModel.computeWeekDays(startDate, endDate)
        val intent = Intent(MainActivity.resultReceiver)
        // You can also include some extra data.
        intent.putExtra(MainActivity.result, result)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

}