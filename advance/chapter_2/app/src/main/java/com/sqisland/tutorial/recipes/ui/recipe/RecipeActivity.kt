package com.sqisland.tutorial.recipes.ui.recipe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sqisland.tutorial.recipes.R
import com.sqisland.tutorial.recipes.data.local.RecipeStore
import com.sqisland.tutorial.recipes.injection.RecipeApplication
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity(), RecipeContract.View {
    companion object {
        const val KEY_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Step 1: setup UI
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Step 2: Load recipe from store
        val store = RecipeStore(this, "recipes")
        val recipeApp = application as RecipeApplication
        val favorites = recipeApp.getFavorites()
        val presenter = RecipePresenter(store, this, favorites)
        presenter.loadRecipe(intent.getStringExtra(KEY_ID))

        // Step 3: If recipe if null, show error. This is done in the presenter

        // Step 4: If recipe is not null, show recipe

        // Step 5: when title is clicked, toggle favorites
        recipe_title.setOnClickListener {
            presenter.toggleFavorite()
        }

    }

    override fun showRecipeNotFoundError() {
        recipe_title.visibility = View.GONE
        recipe_description.text= getString(R.string.recipe_not_found_text)
    }

    override fun setTitle(title: String?) {
        recipe_title.text = title
    }

    override fun setDescription(desc: String?) {
        recipe_description.text = desc
    }

    override fun setFavorite(isFav: Boolean) {
        recipe_title.isSelected = isFav
    }
}
