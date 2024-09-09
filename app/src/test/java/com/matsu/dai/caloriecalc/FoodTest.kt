package com.matsu.dai.caloriecalc.model

import org.junit.Assert.assertEquals
import org.junit.Test

class FoodTest {

    // トータルの計算が一致しているかチェック
    @Test
    fun testTotalFoodKcal() {
        val food = Food(name = "Test Food", proteinGram = 10.0, fatGram = 5.0, carbohydrateGram = 20.0, quantity = 1)
        val expectedTotalCalories = (10 * 4) + (5 * 9) + (20 * 4) // 165
        assertEquals(expectedTotalCalories, food.totalFoodKcal())
    }

    // タンパク質のみのカロリー計算チェック
    @Test
    fun testProteinKcal() {
        val food = Food(name = "Test Food", proteinGram = 10.0, fatGram = 0.0, carbohydrateGram = 0.0, quantity = 1)
        val expectedProteinCalories = 10 * 4  // 40
        assertEquals(expectedProteinCalories, food.proteinKcal())
    }

    // 脂質のみのカロリー計算チェック
    @Test
    fun testFatKcal() {
        val food = Food(name = "Test Food", proteinGram = 0.0, fatGram = 5.0, carbohydrateGram = 0.0, quantity = 1)
        val expectedFatCalories = 5 * 9  // 45
        assertEquals(expectedFatCalories, food.fatKcal())
    }

    // 炭水化物のみのカロリー計算チェック
    @Test
    fun testCarbohydrateKcal() {
        val food = Food(name = "Test Food", proteinGram = 0.0, fatGram = 0.0, carbohydrateGram = 20.0, quantity = 1)
        val expectedCarbohydrateCalories = 20 * 4  // 80
        assertEquals(expectedCarbohydrateCalories, food.carbohydrateKcal())
    }

    // カロリーのまるめ計算をチェック
    @Test
    fun testCalculateCalWithKcalRounding() {
        // タンパク質のまるめ計算チェック
        val food = Food(name = "Test Food", proteinGram = 10.556, fatGram = 0.0, carbohydrateGram = 0.0, quantity = 1)
        val expectedProteinCalories = 42
        assertEquals(expectedProteinCalories, food.proteinKcal())

        // 脂質のまるめ計算チェック
        food.fatGram = 5.123
        val expectedFatCalories = 46
        assertEquals(expectedFatCalories, food.fatKcal())

        // 炭水化物のまるめ計算チェック
        food.carbohydrateGram = 20.999
        val expectedCarbohydrateCalories = 84
        assertEquals(expectedCarbohydrateCalories, food.carbohydrateKcal())

        // 食べ物全体のカロリー計算がまるめ含め正しいことのチェック
        val expectedTotalCal = expectedProteinCalories + expectedFatCalories + expectedCarbohydrateCalories
        assertEquals(expectedTotalCal, food.totalFoodKcal())
    }

    // グラムのまるめ計算をチェック
    @Test
    fun testGramRounding() {
        // タンパク質のまるめ計算チェック
        val food = Food(name = "Test Food", proteinGram = 10.556, fatGram = 0.0, carbohydrateGram = 0.0, quantity = 1)
        val expectedProteinCalories = 10.6
        assertEquals(expectedProteinCalories, food.proteinRoundedGram(), 0.0)

        // 脂質のまるめ計算チェック
        food.fatGram = 5.123
        val expectedFatCalories = 5.1
        assertEquals(expectedFatCalories, food.fatRoundedGram(), 0.0)

        // 炭水化物のまるめ計算チェック
        food.carbohydrateGram = 20.999
        val expectedCarbohydrateCalories = 21.0
        assertEquals(expectedCarbohydrateCalories, food.carbohydrateRoundedGram(), 0.0)
    }

    // グラムのまるめ計算でマイナスの値が入った場合のチェック
    @Test
    fun testGramRoundingWithNegativeInput() {
        // タンパク質のまるめ計算チェック
        val food = Food(name = "Test Food", proteinGram = -10.556, fatGram = 0.0, carbohydrateGram = 0.0, quantity = 1)
        val expectedProteinCalories = 0.0
        assertEquals(expectedProteinCalories, food.proteinRoundedGram(), 0.0)

        // 脂質のまるめ計算チェック
        food.fatGram = -5.123
        val expectedFatCalories = 0.0
        assertEquals(expectedFatCalories, food.fatRoundedGram(), 0.0)

        // 炭水化物のまるめ計算チェック
        food.carbohydrateGram = -20.999
        val expectedCarbohydrateCalories = 0.0
        assertEquals(expectedCarbohydrateCalories, food.carbohydrateRoundedGram(), 0.0)
    }

    // マイナス入力があった場合のカロリー計算チェック
    @Test
    fun testCalculateKcalWithNegativeInput() {
        val food = Food(name = "Test Food", proteinGram = -10.0, fatGram = -5.0, carbohydrateGram = -20.0, quantity = 1)
        assertEquals(0, food.totalFoodKcal())
        assertEquals(0, food.proteinKcal())
        assertEquals(0, food.fatKcal())
        assertEquals(0, food.carbohydrateKcal())
    }
}