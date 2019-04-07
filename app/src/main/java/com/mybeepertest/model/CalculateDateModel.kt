package com.mybeepertest.model

import android.content.Context
import ikartiks.expensetracker.dao.AppDatabase
import java.util.*
import java.util.concurrent.TimeUnit

class CalculateDateModel(con: Context) {

    private var context: Context = con

    private fun getHolidaysInDateRange(startDate: Date, endDate: Date): Int {
        val appDatabase = AppDatabase.getInstance(context)
        val holidays = appDatabase.appDao().getHolidaysBetweenDates(startDate, endDate)
        return holidays.size
    }

    fun computeWeekDays(startDate: Date, endDate: Date): Long {

        val weekDays = getWorkingDaysBetweenTwoDates(startDate, endDate)
        val holidays = getHolidaysInDateRange(startDate, endDate)
        return weekDays - holidays
    }

    companion object {

        fun getWorkingDaysBetweenTwoDates(start: Date, end: Date): Long {

            var start = start
            var end = end

            var totalWeekdays: Long = 0
            if ((end.getTime() - start.getTime()) == 0L)
                return 0
            if (end.getTime() - start.getTime() < 0) {
                val temp = end
                end = start
                start = temp
            }

            val startDate = Calendar.getInstance()
            //startDate.setTime(start);
            startDate.set(start.getYear(), start.getMonth(), start.getDate())

            val endDate = Calendar.getInstance()
            endDate.set(end.getYear(), end.getMonth(), end.getDate())

            //ignoring the 1st and last day
            startDate.add(Calendar.DATE, 1)
            endDate.add(Calendar.DATE, -1)

            //		System.out.println(startDate.get(Calendar.YEAR)+"/"+startDate.get(Calendar.MONTH)+"/"+startDate.get(Calendar.DAY_OF_MONTH)+"");
            //		System.out.println(endDate.get(Calendar.YEAR)+"/"+endDate.get(Calendar.MONTH)+"/"+endDate.get(Calendar.DAY_OF_MONTH)+"");

            val diff = endDate.getTimeInMillis() - startDate.getTimeInMillis()
            val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

            val numberOfWeeks = days / 7
            val remainingDays = days % 7

            //remove saturdays and sundays comming in the number of weeks
            if (numberOfWeeks >= 1) {
                totalWeekdays += numberOfWeeks * 5
            }

            val dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK)

            //sunday 1, saturday 7

            //System.out.println("days "+days+", weeks "+numberOfWeeks+" remainingDays "+remainingDays + " day of week "+dayOfWeek +" totalweekdays "+totalWeekdays);

            //calculation for remaning days of the week
            if (remainingDays > 0) {


                //monday 2
                if (dayOfWeek == Calendar.MONDAY) {
                    if (remainingDays <= 4)
                        totalWeekdays += remainingDays
                    else if (remainingDays == 5L || remainingDays == 6L) {
                        totalWeekdays += 4
                    }
                }

                //tuesday 3
                if (dayOfWeek == Calendar.TUESDAY) {
                    if (remainingDays <= 3)
                        totalWeekdays += remainingDays
                    else if (remainingDays == 6L) {
                        totalWeekdays += 4
                    } else if (remainingDays == 4L || remainingDays == 5L) {
                        totalWeekdays += 3
                    }
                }

                //wednesday 4
                if (dayOfWeek == Calendar.WEDNESDAY) {
                    if (remainingDays <= 2)
                        totalWeekdays += remainingDays
                    else if (remainingDays >= 5) {
                        totalWeekdays = totalWeekdays + remainingDays - 2
                    } else if (remainingDays == 3L || remainingDays == 4L) {
                        totalWeekdays += 2
                    }
                }

                //thursday 5
                if (dayOfWeek == Calendar.THURSDAY) {
                    if (remainingDays <= 1)
                        totalWeekdays += remainingDays
                    else if (remainingDays >= 4) {
                        totalWeekdays = totalWeekdays + remainingDays - 2
                    } else if (remainingDays == 2L || remainingDays == 3L) {
                        totalWeekdays += 1
                    }
                }

                //friday 6
                if (dayOfWeek == Calendar.FRIDAY) {
                    if (remainingDays > 2) {
                        totalWeekdays = totalWeekdays + remainingDays - 2
                    }
                }

                //saturday 7
                if (dayOfWeek == Calendar.SATURDAY) {
                    if (remainingDays > 1)
                        totalWeekdays = totalWeekdays + remainingDays - 2// we dont have to count saturday too
                }

                //sunday 1
                if (dayOfWeek == Calendar.SUNDAY) {
                    if (remainingDays == 6L)
                        totalWeekdays = totalWeekdays + remainingDays - 1
                    else
                        totalWeekdays += remainingDays
                }

                if (!(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)) {
                    totalWeekdays++// since we have to count that particular monday to friday as well, the case of day of week
                }
            }
            return totalWeekdays
        }
    }


}