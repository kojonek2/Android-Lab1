package pl.pwr.adam.zmuda.lab1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculationDao {

    @Insert
    fun insertCalculations(vararg calculation: Calculation)

    @Delete
    fun deleteCalculations(vararg calculation: Calculation)

    @Query("SELECT * FROM calculation ORDER BY timeStamp DESC LIMIT 10")
    fun load10Calculations(): LiveData<List<Calculation>>
}