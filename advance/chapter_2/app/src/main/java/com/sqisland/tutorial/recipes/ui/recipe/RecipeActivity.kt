package com.sqisland.tutorial.recipes.ui.recipe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sqisland.tutorial.recipes.R
import com.sqisland.tutorial.recipes.data.local.RecipeStore
import com.sqisland.tutorial.recipes.data.local.SharedPrefFavorites
import com.sqisland.tutorial.recipes.injection.RecipeApplication
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {
    companion object {
        const val KEY_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val store = RecipeStore(this, "recipes")
        val recipe = store.getRecipe(intent.getStringExtra(KEY_ID))
        if (recipe == null) {
            recipe_title.visibility = View.GONE
            recipe_description.text= getString(R.string.recipe_not_found_text)
            return;
        }
        val recipeApp = application as RecipeApplication
        val favorites = recipeApp.getFavorites()
        val isFav = favorites.get(recipe.id)
        recipe_title.text = recipe.title
        recipe_title.isSelected = isFav
        recipe_title.setOnClickListener {
            val result = favorites.toogle(recipe.id)
            it.isSelected = result
        }
        recipe_description.text = recipe.description
    }
}
