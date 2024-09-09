package com.matsu.dai.caloriecalc.model

import java.math.BigDecimal
import java.math.RoundingMode


data class Food(
    var name: String,
    var proteinGram: Double,
    var fatGram: Double,
    var carbohydrateGram: Double,
    var quantity: Int
) {

    enum class Nutrient(val calPerGram: Double) {
        PROTEIN(4.0),
        FAT(9.0),
        CARBOHYDRATE(4.0)
    }

    fun totalFoodKcal(): Int {
        return proteinKcal() + fatKcal() + carbohydrateKcal()
    }

    fun proteinKcal(): Int {
        return calculateKcal(proteinGram, Nutrient.PROTEIN)
    }

    fun fatKcal(): Int {
        return calculateKcal(fatGram, Nutrient.FAT)
    }

    fun carbohydrateKcal(): Int {
        return calculateKcal(carbohydrateGram, Nutrient.CARBOHYDRATE)
    }

    fun proteinRoundedGram(): Double {
        return roundedGram(proteinGram)
    }

    fun fatRoundedGram(): Double {
        return roundedGram(fatGram)
    }

    fun carbohydrateRoundedGram(): Double {
        return roundedGram(carbohydrateGram)
    }

    private fun roundedGram(inputGram: Double): Double {
        if (inputGram < 0.0) return 0.0
        return BigDecimal(inputGram).setScale(1, RoundingMode.HALF_UP).toDouble()
    }

    private fun calculateKcal(inputGram: Double, nutrient: Nutrient): Int {
        val result = roundedGram(inputGram) * nutrient.calPerGram
        val roundedResult = BigDecimal(result).setScale(0, RoundingMode.HALF_UP)
        return roundedResult.toInt()
    }
}