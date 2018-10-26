package com.sqisland.tutorial.recipes.ui.recipe

import com.sqisland.tutorial.recipes.data.local.Favorites
import com.sqisland.tutorial.recipes.data.local.RecipeStore
import com.sqisland.tutorial.recipes.data.model.Recipe
import java.lang.IllegalStateException

class RecipePresenter(private val store: RecipeStore,
                      private val view: RecipeContract.View,
                      private val favorites: Favorites): RecipeContract.Listener {
    var recipe : Recipe? = null

    fun loadRecipe(id: String) {
        recipe = store.getRecipe(id)
        recipe?.let {
            view.setTitle(it.title)
            view.setDescription(it.description)
            view.setFavorite(favorites.get(it.id))
        } ?: view.showRecipeNotFoundError()
    }

    fun toggleFavorite() {
        recipe?.let {
            val result = favorites.toogle(it.id)
            view.setFavorite(result)
        } ?: throw IllegalStateException()

    }
}