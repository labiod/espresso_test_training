package com.sqisland.tutorial.recipes.test

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not

abstract class ScreenRobot<T : ScreenRobot<T>> {

    fun checkIsHidden(@IdRes vararg viewsIds: Int): T {
        viewsIds.forEach {
            onView(withId(it))
                .check(matches(not(isDisplayed())))
        }
        return this as T
    }

    fun checkViewHasText(@IdRes viewsId: Int, @StringRes stringId: Int): T {
        onView(withId(viewsId))
            .check(matches(withText(stringId)))
        return this as T
    }

    fun checkIsSelected(@IdRes vararg viewsIds: Int): T {
        viewsIds.forEach {
            onView(withId(it))
                .check(matches(isSelected()))
        }
        return this as T
    }
}