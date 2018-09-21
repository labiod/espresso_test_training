package com.sqisland.tutorial.recipes.ui.recipe

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.sqisland.tutorial.recipes.R
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites
import com.sqisland.tutorial.recipes.injection.TestRecipeApplication
import com.sqisland.tutorial.recipes.test.RecipeRobot
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeActivityTest {
    @Rule @JvmField
    val activityRule = ActivityTestRule(RecipeActivity::class.java, true, false)

    @Test
    fun recipeNotFound() {
        RecipeRobot()
            .launch(activityRule)
            .notTitle()
            .description(R.string.recipe_not_found_text)
    }

    @Test
    fun clickToFavorite() {
        RecipeRobot()
            .launch(activityRule, CARROTS_ID)

        onView(withId(R.id.recipe_title))
            .check(matches(withText("Creamed Carrots")))
            .check(matches(not(isSelected())))
            .perform(click())
            .check(matches(isSelected()))
    }

    @Test
    fun alreadyFavorite() {
        RecipeRobot()
            .setFavorite(CARROTS_ID)
            .launch(activityRule, CARROTS_ID)
            .isFavorite()
    }

    fun launchRecipe(id: String) {
        val intent = Intent()
        intent.putExtra(RecipeActivity.KEY_ID, id)
        activityRule.launchActivity(intent)
    }

    companion object {
        const val CARROTS_ID = "creamed_carrots"
    }
}