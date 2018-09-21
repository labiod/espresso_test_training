package com.sqisland.tutorial.recipes.injection

import com.sqisland.tutorial.recipes.data.local.Favorites
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites

class TestRecipeApplication : RecipeApplication() {
    private val favorites = InMemoryFavorites()
    override fun getFavorites(): Favorites {
        return favorites
    }
}