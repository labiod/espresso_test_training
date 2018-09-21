package com.sqisland.tutorial.recipes.test

import android.app.Activity
import android.content.Intent
import android.support.annotation.StringRes
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.sqisland.tutorial.recipes.R
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites
import com.sqisland.tutorial.recipes.injection.TestRecipeApplication
import com.sqisland.tutorial.recipes.ui.recipe.RecipeActivity
import com.sqisland.tutorial.recipes.ui.recipe.RecipeActivityTest
import kotlinx.android.synthetic.main.activity_recipe.*
import org.junit.Before

class RecipeRobot : ScreenRobot<RecipeRobot>() {

    private val favorite : InMemoryFavorites

    init {
        val app = InstrumentationRegistry.getTargetContext().applicationContext as TestRecipeApplication
        favorite = app.getFavorites() as InMemoryFavorites
        favorite.clear()
    }

    fun notTitle(): RecipeRobot {
        return checkIsHidden(R.id.recipe_title)
    }

    fun launch(rule: ActivityTestRule<RecipeActivity>): RecipeRobot {
        rule.launchActivity(null)
        return this
    }

    fun launch(rule: ActivityTestRule<RecipeActivity>, id: String): RecipeRobot {
        val intent = Intent()
        intent.putExtra(RecipeActivity.KEY_ID, id)
        rule.launchActivity(intent)
        return this
    }

    fun description(@StringRes strindId: Int): RecipeRobot {
        return checkViewHasText(R.id.recipe_description, strindId)
    }

    fun setFavorite(id: String) : RecipeRobot {
        favorite.put(id, true)
        return this
    }

    fun isFavorite() : RecipeRobot {
        return checkIsSelected(R.id.recipe_title)
    }
}