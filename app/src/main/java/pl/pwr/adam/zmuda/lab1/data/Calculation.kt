package pl.pwr.adam.zmuda.lab1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.pwr.adam.zmuda.lab1.MainActivity

@Entity(tableName = "calculation")
data class Calculation(
    val mass: String,
    val height: String,
    val bmi: String,
    val selectedUnit: MainActivity.Units,
    val timeStamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}