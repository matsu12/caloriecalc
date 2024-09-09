package com.matsu.dai.caloriecalc

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.matsu.dai.caloriecalc.databinding.ItemFoodBinding
import com.matsu.dai.caloriecalc.model.Food

open class FoodAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _foodPairList: MutableList<Pair<Food, Int>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding: ItemFoodBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_food, parent, false)
        return FoodViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FoodViewHolder) {
            holder.bind(_foodPairList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return _foodPairList.size
    }

    open fun setFood(foodPair: Pair<Food, Int>) {
        _foodPairList.add(foodPair)
        notifyItemRangeChanged(0, _foodPairList.size)
    }

    open fun addFood(foodPair: Pair<Food, Int>) {
        _foodPairList.add(foodPair)
        notifyItemInserted(_foodPairList.size)
    }

    class FoodViewHolder(private val binding: ItemFoodBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        val foodName: ObservableField<String> = ObservableField()
        val protein: ObservableField<String> = ObservableField()
        val fat: ObservableField<String> = ObservableField()
        val carbohydrate: ObservableField<String> = ObservableField()
        val cal: ObservableField<String> = ObservableField()

        fun bind(foodPair: Pair<Food, Int>) {
            binding.viewHolder = this
            val food = foodPair.first
            foodName.set(food.name)
            protein.set(context.getString(R.string.item_protein, food.proteinRoundedGram().toString()))
            fat.set(context.getString(R.string.item_fat, food.fatRoundedGram().toString()))
            carbohydrate.set(context.getString(R.string.item_carbohydrate, food.carbohydrateRoundedGram().toString()))
            cal.set(context.getString(R.string.item_food_cal, foodPair.second.toString()))
        }
    }
}