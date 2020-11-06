package pl.pwr.adam.zmuda.lab1.BMI

import org.junit.Test

import org.junit.Assert.*

class BMICalculatorMetricTest {

    @Test
    fun calculateCorrectBmi() {
        val calculator: BMICalculator = BMICalculatorMetric()

        val bmi = calculator.calculateBmi( 180, 65)

        assertEquals(20.1, bmi, 0.1)
    }

    @Test
    fun calculateCorrectBmi2() {
        val calculator: BMICalculator = BMICalculatorMetric()

        val bmi = calculator.calculateBmi( 168, 80)

        assertEquals(28.3, bmi, 0.1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun heightToLow()
    {
        val calculator: BMICalculator = BMICalculatorMetric()
        calculator.calculateBmi( calculator.getMinHeight() - 1, 145)
    }

    @Test(expected = IllegalArgumentException::class)
    fun heightToHigh()
    {
        val calculator: BMICalculator = BMICalculatorMetric()
        calculator.calculateBmi( calculator.getMaxHeight() + 1, 145)
    }

    @Test(expected = IllegalArgumentException::class)
    fun weightToLow()
    {
        val calculator: BMICalculator = BMICalculatorMetric()
        calculator.calculateBmi( 56, calculator.getMinWeight() - 1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun weightToHigh()
    {
        val calculator: BMICalculator = BMICalculatorMetric()
        calculator.calculateBmi( 56, calculator.getMaxWeight() + 1)
    }

}