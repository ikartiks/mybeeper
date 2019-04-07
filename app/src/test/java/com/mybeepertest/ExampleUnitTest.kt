package com.mybeepertest

import com.mybeepertest.model.CalculateDateModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkWeekDaysInRangeWithoutHolidays() {

        val weekDays1 = CalculateDateModel.getWorkingDaysBetweenTwoDates( Date(2014, 7, 7),  Date(2014, 7,11))
        val weekDays2 = CalculateDateModel.getWorkingDaysBetweenTwoDates( Date(2014, 7, 13),  Date(2014, 7,21))

        assertEquals(1, weekDays1)
        assertEquals(5, weekDays2)
    }
}
