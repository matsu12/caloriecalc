package com.matsu.dai.caloriecalc


import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    var context: Context? = null
    var device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun before() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun mainActivityTest() {
        mActivityScenarioRule.scenario

        val noticeTextView = onView(
            allOf(
                withId(R.id.notice),
                withText(context?.getString(R.string.input_and_out_put_notice)),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        noticeTextView.check(matches(withText(context?.getString(R.string.input_and_out_put_notice))))

        // それぞれの入力フォームが表示されていることをチェック
        val proteinGroup = onView(
            allOf(
                withId(R.id.protein),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        proteinGroup.check(matches(isDisplayed()))

        val proteinTextView = onView(
            allOf(
                withId(R.id.input_title), withText(context?.getString(R.string.protein)),
                withParent(
                    allOf(
                        withId(R.id.protein),
                        withParent(withId(R.id.main))
                    )
                ),
                isDisplayed()
            )
        )
        proteinTextView.check(matches(withText(context?.getString(R.string.protein))))


        val fatGroup = onView(
            allOf(
                withId(R.id.fat),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        fatGroup.check(matches(isDisplayed()))

        val fatTextView = onView(
            allOf(
                withId(R.id.input_title), withText(context?.getString(R.string.fat)),
                withParent(
                    allOf(
                        withId(R.id.fat),
                        withParent(withId(R.id.main))
                    )
                ),
                isDisplayed()
            )
        )
        fatTextView.check(matches(withText(context?.getString(R.string.fat))))

        val carbohydrateGroup = onView(
            allOf(
                withId(R.id.carbohydrate),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        carbohydrateGroup.check(matches(isDisplayed()))

        val carbohydrateTextView = onView(
            allOf(
                withId(R.id.input_title), withText(context?.getString(R.string.carbohydrate)),
                withParent(
                    allOf(
                        withId(R.id.carbohydrate),
                        withParent(withId(R.id.main))
                    )
                ),
                isDisplayed()
            )
        )
        carbohydrateTextView.check(matches(withText(context?.getString(R.string.carbohydrate))))


        val foodNameGroup = onView(
            allOf(
                withId(R.id.foodName),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        foodNameGroup.check(matches(isDisplayed()))

        val foodNameTextView = onView(
            allOf(
                withId(R.id.input_title), withText(context?.getString(R.string.food_name)),
                withParent(
                    allOf(
                        withId(R.id.foodName),
                        withParent(withId(R.id.main))
                    )
                ),
                isDisplayed()
            )
        )
        foodNameTextView.check(matches(withText(context?.getString(R.string.food_name))))

        // 追加ボタンが最初はタップできないことをチェック
        val addButton = onView(
            allOf(
                withId(R.id.add_button), withText(context?.getString(R.string.add)),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        addButton.check(matches(isNotEnabled()))
        addButton.check(matches(isDisplayed()))

        // 丸めのチェックもするため少数点以下の桁数を増やして設定する
        val proteinEditText = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.protein),
                        childAtPosition(
                            withId(R.id.main),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        proteinEditText.perform(replaceText("11.02345"), closeSoftKeyboard())

        val fatEditText = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.fat),
                        childAtPosition(
                            withId(R.id.main),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        fatEditText.perform(replaceText("0.9876"), closeSoftKeyboard())

        val carbohydrateEditText = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.carbohydrate),
                        childAtPosition(
                            withId(R.id.main),
                            3
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        carbohydrateEditText.perform(replaceText("1111.03131"), closeSoftKeyboard())

        val foodNameEditText = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.foodName),
                        childAtPosition(
                            withId(R.id.main),
                            4
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        foodNameEditText.perform(replaceText("白米"))
        foodNameEditText.perform(closeSoftKeyboard())

        // 追加ボタンが表示されているかつタップ可能かチェック
        addButton.check(matches(isDisplayed()))
        addButton.check(matches(isEnabled()))
        addButton.perform(click())

        // 合計カロリーが表示されているかチェック
        device.wait(Until.hasObject(By.res("${context?.packageName}:id/totalCal")), 10000)
        val totalCal: UiObject2 = device.findObject(By.res("${context?.packageName}:id/totalCal"))
        assertThat(totalCal.text, containsString("合計"))

        val totalCalView = onView(
            allOf(
                withId(R.id.totalCal),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        totalCalView.check(matches(withText("合計カロリー: 4497 kcal")))

        proteinEditText.check(matches(withText("")))
        fatEditText.check(matches(withText("")))
        carbohydrateEditText.check(matches(withText("")))
        foodNameEditText.check(matches(withText("")))

        // 入力した情報を表示するrecyclerViewが表示されていることのチェック
        val recyclerView = onView(
            allOf(
                withId(R.id.input_food_list_recycler),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        // recyclerViewにアイテムが追加されているかチェック
        val proteinQuantityView = onView(
            allOf(
                withId(R.id.protein_quantity),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.recyclerview.widget.RecyclerView::class.java))),
                isDisplayed()
            )
        )
        proteinQuantityView.check(matches(withText("タンパク質： 11.0 g")))

        val fatQuantityView = onView(
            allOf(
                withId(R.id.fat_quantity),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.recyclerview.widget.RecyclerView::class.java))),
                isDisplayed()
            )
        )
        fatQuantityView.check(matches(withText("脂質質： 1.0 g")))

        val carbohydrateQuantityView = onView(
            allOf(
                withId(R.id.carbohydrate_quantity),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.recyclerview.widget.RecyclerView::class.java))),
                isDisplayed()
            )
        )
        carbohydrateQuantityView.check(matches(withText("炭水化物： 1111.0 g")))

        val foodCalView = onView(
            allOf(
                withId(R.id.food_cal),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.recyclerview.widget.RecyclerView::class.java))),
                isDisplayed()
            )
        )
        foodCalView.check(matches(withText("カロリー： 4497 kcal")))

        val foodNameView = onView(
            allOf(
                withId(R.id.name),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.recyclerview.widget.RecyclerView::class.java))),
                isDisplayed()
            )
        )
        foodNameView.check(matches(withText("白米")))


        //もう一度繰り返して合計が更新されているかチェック
        proteinEditText.perform(replaceText("11"), closeSoftKeyboard())
        fatEditText.perform(replaceText("1"), closeSoftKeyboard())
        carbohydrateEditText.perform(replaceText("1111"), closeSoftKeyboard())
        foodNameEditText.perform(replaceText("白米"))
        addButton.check(matches(isDisplayed()))
        addButton.check(matches(isEnabled()))
        addButton.perform(click())
        device.wait(Until.hasObject(By.res("${context?.packageName}:id/totalCal")), 10000)
        assertThat(totalCal.text, containsString("合計"))

        proteinEditText.check(matches(withText("")))
        fatEditText.check(matches(withText("")))
        carbohydrateEditText.check(matches(withText("")))
        foodNameEditText.check(matches(withText("")))
        totalCalView.check(matches(withText("合計カロリー: 8994 kcal")))

        //マイナスの値が入った場合に合計が更新されないことをチェック
        proteinEditText.perform(replaceText("-1"), closeSoftKeyboard())
        fatEditText.perform(replaceText("-1"), closeSoftKeyboard())
        carbohydrateEditText.perform(replaceText("-1111"), closeSoftKeyboard())
        foodNameEditText.perform(replaceText("白米"))
        addButton.check(matches(isDisplayed()))
        addButton.check(matches(isEnabled()))
        addButton.perform(click())
        device.wait(Until.hasObject(By.res("${context?.packageName}:id/totalCal")), 10000)
        assertThat(totalCal.text, containsString("合計"))

        proteinEditText.check(matches(withText("")))
        fatEditText.check(matches(withText("")))
        carbohydrateEditText.check(matches(withText("")))
        foodNameEditText.check(matches(withText("")))
        totalCalView.check(matches(withText("合計カロリー: 8994 kcal")))


        //数値以外が入った場合に合計が更新されないことをチェック
        proteinEditText.perform(replaceText("aaaa"), closeSoftKeyboard())
        fatEditText.perform(replaceText("aaa"), closeSoftKeyboard())
        carbohydrateEditText.perform(replaceText("aaaaa"), closeSoftKeyboard())
        foodNameEditText.perform(replaceText("白米"))
        addButton.check(matches(isDisplayed()))
        addButton.check(matches(isEnabled()))
        addButton.perform(click())
        device.wait(Until.hasObject(By.res("${context?.packageName}:id/totalCal")), 10000)
        assertThat(totalCal.text, containsString("合計"))

        proteinEditText.check(matches(withText("")))
        fatEditText.check(matches(withText("")))
        carbohydrateEditText.check(matches(withText("")))
        foodNameEditText.check(matches(withText("")))
        totalCalView.check(matches(withText("合計カロリー: 8994 kcal")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
