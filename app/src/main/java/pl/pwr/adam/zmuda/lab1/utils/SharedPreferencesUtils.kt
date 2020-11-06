package pl.pwr.adam.zmuda.lab1.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.pwr.adam.zmuda.lab1.data.Calculation
import java.lang.reflect.Type
import java.util.*

object SharedPreferencesUtils
{
    const val CALCULATION_HISTORY: String = "CALCULATION_HISTORY"
    const val SHARED_PREFERENCES_KEY: String = "BMI_CALCULATOR_PREFS"

    fun readHistory(sharedPref: SharedPreferences) : LinkedList<Calculation>
    {
        try {
            val calculations = sharedPref.getString(CALCULATION_HISTORY, "[]")
            val gson = Gson()

            val type: Type = object : TypeToken<LinkedList<Calculation>>(){}.type
            return gson.fromJson(calculations, type)

        } catch (e: Exception) {
            return LinkedList()
        }
    }

    fun saveHistory(sharedPref: SharedPreferences, calculations: LinkedList<Calculation>)
    {
        val gson = Gson()

        with (sharedPref.edit()) {
            putString(CALCULATION_HISTORY, gson.toJson(calculations))
            apply()
        }
    }
}