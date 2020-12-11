package pl.pwr.adam.zmuda.lab1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Calculation::class], version = 1)
@TypeConverters(Converter::class)
abstract class CalculationDatabase : RoomDatabase() {
    abstract fun calculationDao() : CalculationDao

    companion object {
        private const val DATABASE_NAME = "Calculations"

        private var database: CalculationDatabase? = null

        fun getInstance(applicationContext: Context) : CalculationDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    applicationContext,
                    CalculationDatabase::class.java, DATABASE_NAME
                ).build()
            }
            return database!!
        }
    }
}

