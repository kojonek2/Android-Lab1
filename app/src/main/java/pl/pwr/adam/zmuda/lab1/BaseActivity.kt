package pl.pwr.adam.zmuda.lab1

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

open class BaseActivity : AppCompatActivity(), SensorEventListener {

    companion object {
        const val THRESHOLD = 10
    }

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        val currentValue = event.values[0]
        val newNightMode: Int
        val currentNightMode = AppCompatDelegate.getDefaultNightMode()

        if (currentValue < THRESHOLD)
            newNightMode = AppCompatDelegate.MODE_NIGHT_YES
        else
            newNightMode = AppCompatDelegate.MODE_NIGHT_NO

        if (currentNightMode != newNightMode) {
            AppCompatDelegate.setDefaultNightMode(newNightMode)
        }
    }
}