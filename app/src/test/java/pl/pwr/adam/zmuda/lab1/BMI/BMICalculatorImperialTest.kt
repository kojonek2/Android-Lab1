package pl.pwr.adam.zmuda.lab1.BMI

import org.junit.Test

import org.junit.Assert.*

class BMICalculatorImperialTest {

    @Test
    fun calculateCorrectBmi() {
        val calculator: BMICalculator = BMICalculatorImperial()

        val bmi = calculator.calculateBmi( 72, 160)

        assertEquals(21.7, bmi, 0.1)
    }

    @Test
    fun calculateCorrectBmi2() {
        val calculator: BMICalculator = BMICalculatorImperial()

        val bmi = calculator.calculateBmi( 56, 145)

        assertEquals(32.5, bmi, 0.1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun heightToLow()
    {
        val calculator: BMICalculator = BMICalculatorImperial()
        calculator.calculateBmi( calculator.getMinHeight() - 1, 145)
    }

    @Test(expected = IllegalArgumentException::class)
    fun heightToHigh()
    {
        val calculator: BMICalculator = BMICalculatorImperial()
        calculator.calculateBmi( calculator.getMaxHeight() + 1, 145)
    }

    @Test(expected = IllegalArgumentException::class)
    fun weightToLow()
    {
        val calculator: BMICalculator = BMICalculatorImperial()
        calculator.calculateBmi( 56, calculator.getMinWeight() - 1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun weightToHigh()
    {
        val calculator: BMICalculator = BMICalculatorImperial()
        calculator.calculateBmi( 56, calculator.getMaxWeight() + 1)
    }

}