package com.matsu.dai.caloriecalc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.matsu.dai.caloriecalc.model.Food

class MainViewModel(app: Application): AndroidViewModel(app) {

    var inputProtein: MutableLiveData<String?> = MutableLiveData()
    var inputFat: MutableLiveData<String?> = MutableLiveData()
    var inputCarbohydrate: MutableLiveData<String?> = MutableLiveData()
    var inputError: MutableLiveData<String?> = MutableLiveData()
    val enabledAddButton = MediatorLiveData<Boolean?>().apply {
        val observer = Observer<String?> {
            val protein = inputProtein.value
            val fat = inputFat.value
            val carbohydrate = inputCarbohydrate.value
            value =
                !protein.isNullOrEmpty() && !fat.isNullOrEmpty() && !carbohydrate.isNullOrEmpty()
        }
        addSource(inputProtein, observer)
        addSource(inputFat, observer)
        addSource(inputCarbohydrate, observer)
    }

    private val _foodList: MutableList<Pair<Food, Int>> = mutableListOf()
    val totalCal: MutableLiveData<String> = MutableLiveData()
    val foodName: MutableLiveData<String?> = MutableLiveData()
    var adapter: FoodAdapter? = null

    fun onAddButton() {
        val food = Food(
            foodName.value ?: "", checkInput(inputProtein.value),
            checkInput(inputFat.value),checkInput(inputCarbohydrate.value),
            1)
        val foodPair = Pair(food, food.totalFoodKcal())
        _foodList.add(foodPair)
        if (adapter?.itemCount == null || (adapter?.itemCount ?: 0) <= 0 ) {
            adapter?.setFood(foodPair)
        } else {
            adapter?.addFood(foodPair)
        }
        totalCal.value = getApplication<Application>().applicationContext.getString(R.string.total_cal, _foodList.sumOf { it.second })
        inputProtein.value = ""
        inputFat.value = ""
        inputCarbohydrate.value = ""
        foodName.value = ""
    }

    fun checkInput(input: String?): Double {
        val value = input?.toDoubleOrNull()
        // null or マイナスの場合は0.0として扱う
        // マイナスは入力されないはずだがチェックはしておく
        return if (value == null || value < 0) {
            0.0
        } else {
            value
        }
    }

    init {
        adapter = FoodAdapter()
    }
}