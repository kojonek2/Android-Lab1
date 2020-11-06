package pl.pwr.adam.zmuda.lab1.BMI

class BMICalculatorImperial : BMICalculator() {

    companion object {
        const val CONVERSION_RATE: Int = 703
    }

    override fun calculateBmi(height: Int, weight: Int): Double {
        checkConstrains(height, weight)

        val heightIn: Double = height.toDouble()
        return CONVERSION_RATE * weight.toDouble() / (heightIn * heightIn)
    }

    override fun getMinHeight(): Int {
        return 39
    }

    override fun getMaxHeight(): Int {
        return 86
    }

    override fun getMinWeight(): Int {
        return 66
    }

    override fun getMaxWeight(): Int {
        return 352
    }

}