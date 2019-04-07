package ikartiks.expensetracker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mybeepertest.entities.Holidays
import java.util.*

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHoliday(vararg account: Holidays)

    @Query("SELECT * FROM holidays WHERE date between :startDate and :endDate and canBeCarryforWorded = :canBeCarryForwarded")//
    fun getHolidaysBetweenDates(startDate: Date, endDate: Date, canBeCarryForwarded: Boolean = true): List<Holidays>//

}