package pl.pwr.adam.zmuda.lab1.BMI

abstract class BMICalculator {

    protected fun checkConstrains(height: Int, weight: Int)
    {
        if (height < getMinHeight() || height > getMaxHeight())
            throw IllegalArgumentException("Height must be in range [" + getMinHeight() + ", " + getMaxHeight() + "]!")

        if (weight < getMinWeight() || weight > getMaxWeight())
            throw IllegalArgumentException("Height must be in range [" + getMinWeight() + ", " + getMaxWeight() + "]!")
    }

    abstract fun calculateBmi(height: Int, weight: Int): Double

    abstract fun getMinHeight(): Int
    abstract fun getMaxHeight(): Int

    abstract fun getMinWeight(): Int
    abstract fun getMaxWeight(): Int
}