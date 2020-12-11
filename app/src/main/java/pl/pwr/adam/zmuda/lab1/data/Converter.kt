package pl.pwr.adam.zmuda.lab1.data

import androidx.room.TypeConverter
import pl.pwr.adam.zmuda.lab1.MainActivity

class Converter {
    @TypeConverter
    fun intToUnit(value: Int?): MainActivity.Units? {
        return value?.let { MainActivity.Units.getByValue(it) }
    }

    @TypeConverter
    fun unitToInt(unity: MainActivity.Units?): Int? {
        return unity?.value
    }
}