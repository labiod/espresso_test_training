package com.sqisland.android.espresso.hermetic

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.joda.time.DateTime
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun evening() {
        val clock = mock(Clock::class.java)
        val app = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
        app.component = DaggerTestComponent.builder()
            .clock(clock)
            .build()
        `when`(clock.getNow()).thenReturn(DateTime().withHourOfDay(20))

        activityRule.launchActivity(null)
        onView(withId(R.id.greeting))
            .check(matches(withText(R.string.greeting_evening)))
    }
}