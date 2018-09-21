package com.sqisland.android.espresso.listview

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule @JvmField
    var activityRule =  ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun clickItem() {
        onView(withId(R.id.footer))
            .check(matches(not(isDisplayed())))

        onData(withValue(27))
            .inAdapterView(withId(R.id.list))
            .perform(click())

        onView(withId(R.id.footer))
            .check(matches(withText("27")))
            .check(matches(isDisplayed()))
    }

    private fun withValue(value: Int) : Matcher<Any> {
        return object : BoundedMatcher<Any, MainActivity.Item>(MainActivity.Item::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("has value $value")
            }

            override fun matchesSafely(item: MainActivity.Item?): Boolean {
                return item?.toString().equals(value.toString())
            }

        }
    }
}