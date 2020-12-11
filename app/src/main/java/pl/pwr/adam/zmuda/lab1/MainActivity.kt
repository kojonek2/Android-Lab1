package pl.pwr.adam.zmuda.lab1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.pwr.adam.zmuda.lab1.BMI.BMICalculator
import pl.pwr.adam.zmuda.lab1.BMI.BMICalculatorImperial
import pl.pwr.adam.zmuda.lab1.BMI.BMICalculatorMetric
import pl.pwr.adam.zmuda.lab1.data.Calculation
import pl.pwr.adam.zmuda.lab1.data.CalculationDatabase
import pl.pwr.adam.zmuda.lab1.databinding.ActivityMainBinding
import java.util.*

class MainActivity : BaseActivity() {

    companion object {
        const val DISPLAYED_BMI: String = "DISPLAYED_BMI"
        const val SELECTED_UNITY: String = "SELECTED_UNIT"


        const val LOWEST_BMI: Int = 15
        const val HIGHEST_BMI: Int = 33
    }

    enum class Units(val value: Int) {
        METRIC(1),
        IMPERIAL(2);

        companion object {
            fun getByValue(value: Int) = values().first{v -> v.value == value}
        }
    }

    private var selectedUnits: Units = Units.METRIC
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putCharSequence(DISPLAYED_BMI, binding.bmiTV.text)
            putSerializable(SELECTED_UNITY, selectedUnits)
        }


        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.apply {
            displayBMI(getCharSequence(DISPLAYED_BMI).toString())
            selectedUnits = get(SELECTED_UNITY) as Units
        }

        setInputLabels()
        super.onRestoreInstanceState(savedInstanceState)
    }

    fun count(view: View) {
        binding.apply {
            val calculator: BMICalculator = getBmiCalculator()

            if (heightET.text.isBlank()) {
                heightET.error = getString(R.string.height_is_empty)
            } else {
                val height = heightET.text.toString().toInt()
                if (height < calculator.getMinHeight() || height > calculator.getMaxHeight())
                {
                    heightET.error = getString(R.string.height_not_in_range, calculator.getMinHeight(), calculator.getMaxHeight())
                }
            }

            if (massET.text.isBlank()) {
                massET.error = getString(R.string.mass_is_empty)
            } else {
                val mass = massET.text.toString().toInt()
                if (mass < calculator.getMinWeight() || mass > calculator.getMaxWeight())
                {
                    massET.error = getString(R.string.weight_not_in_range, calculator.getMinWeight(), calculator.getMaxWeight())
                }
            }

            if ((heightET.error != null && !heightET.error.isBlank()) || (massET.error != null && !massET.error.isBlank())) {
                resetBmiDisplay()
                return
            }

            view.hideKeyboard()

            val bmi = calculator.calculateBmi(heightET.text.toString().toInt(), massET.text.toString().toInt())

            displayBMI(bmi)
            saveBmi()
        }

    }

    private fun resetBmiDisplay() {
        binding.apply {
            bmiSeekArc.progress = 0
            bmiSeekArc.isEnabled = false
            bmiSeekArc.progressColor = getColor(R.color.colorDefault)

            bmiTV.text = "--"
            bmiTV.setTextColor(getColor(R.color.colorDefault))
        }
    }


    private fun displayBMI(bmiText: String) {
        val bmi: Double
        try {
            bmi = bmiText.toDouble()
        } catch (e: Exception) {
            resetBmiDisplay()
            return
        }

        displayBMI(bmi)
    }

    private fun displayBMI(bmi: Double) {
        binding.apply {
            val color = when {
                bmi < 18.5 -> getColor(R.color.colorUnderweight)
                bmi < 25 -> getColor(R.color.colorNormalWeight)
                bmi < 30 -> getColor(R.color.colorOverweight)
                else -> getColor(R.color.colorObese)
            }

            val percentage = (bmi - LOWEST_BMI) * 100 / (HIGHEST_BMI - LOWEST_BMI)
            bmiSeekArc.isEnabled = true
            bmiSeekArc.progress = percentage.toInt()
            bmiSeekArc.progressColor = color

            bmiTV.text = String.format(Locale.US, "%.1f", bmi)
            bmiTV.setTextColor(color)
        }
    }

    private fun saveBmi()
    {
        binding.apply {
            val calculation = Calculation(
                massET.text.toString(),
                heightET.text.toString(),
                bmiTV.text.toString(),
                selectedUnits,
                System.currentTimeMillis())

            val database = CalculationDatabase.getInstance(applicationContext)
            val dao = database.calculationDao()

            GlobalScope.launch {
                dao.insertCalculations(calculation)
            }
        }
    }

    fun openDetails(view: View) {
        val bmi: Float

        try {
            bmi = binding.bmiTV.text.toString().toFloat()
        }
        catch(e: NumberFormatException)
        {
            return //No calculations performed
        }

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.BMI_PARAM, bmi)
        startActivity(intent)
    }

    private fun getBmiCalculator(): BMICalculator
    {
        if (selectedUnits == Units.METRIC)
            return BMICalculatorMetric()
        else
            return BMICalculatorImperial()
    }

    fun changeUnits(item: MenuItem) {
        if (selectedUnits == Units.METRIC)
            selectedUnits = Units.IMPERIAL
        else
            selectedUnits = Units.METRIC

        setInputLabels()
    }

    fun viewHistory(item: MenuItem) {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    private fun setInputLabels() {
        binding.apply {
            if (selectedUnits == Units.METRIC) {
                heightTV.text = getString(R.string.height_cm)
                massTV.text = getString(R.string.mass_kg)
            }
            else {
                heightTV.text = getString(R.string.height_in)
                massTV.text = getString(R.string.mass_lb)
            }
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}