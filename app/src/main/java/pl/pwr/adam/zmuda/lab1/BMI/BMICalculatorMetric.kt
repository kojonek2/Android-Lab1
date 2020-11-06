package pl.pwr.adam.zmuda.lab1.BMI

class BMICalculatorMetric : BMICalculator() {

    override fun calculateBmi(height: Int, weight: Int): Double {
        checkConstrains(height, weight)

        val heightInCm: Double = height.toDouble() / 100.0
        return weight.toDouble() / (heightInCm * heightInCm)
    }

    override fun getMinHeight(): Int {
        return 100
    }

    override fun getMaxHeight(): Int {
        return 220
    }

    override fun getMinWeight(): Int {
        return 30
    }

    override fun getMaxWeight(): Int {
        return 160
    }

}