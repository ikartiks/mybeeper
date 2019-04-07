package com.mybeepertest

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.mybeepertest.model.CalculateDateModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.mybeepertest", appContext.packageName)
    }

    @Test
    fun checkWeekDaysInRangeWithHolidays() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val calculateDateModel = CalculateDateModel(appContext)

        val days1 = calculateDateModel.computeWeekDays(Date(2019, 3, 7),  Date(2019, 3,12))
        val days2 = calculateDateModel.computeWeekDays(Date(2019, 3, 7),  Date(2019, 3,30))

        assertEquals(4, days1)
        assertEquals(11, days2)
    }


}
