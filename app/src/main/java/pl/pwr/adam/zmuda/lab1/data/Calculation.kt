package pl.pwr.adam.zmuda.lab1.data

import pl.pwr.adam.zmuda.lab1.MainActivity

class Calculation(
    val mass: String,
    val height: String,
    val bmi: String,
    selectedUnit: MainActivity.Units,
    val timeStamp: Long
) {
    private val selectedUnityInt: Int = selectedUnit.value

    val selectedUnit: MainActivity.Units
    get() = MainActivity.Units.getByValue(selectedUnityInt)
}