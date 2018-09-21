package com.sqisland.tutorial.recipes.injection

import android.app.Application
import com.sqisland.tutorial.recipes.data.local.Favorites
import com.sqisland.tutorial.recipes.data.local.SharedPrefFavorites

open class RecipeApplication : Application() {
    private var favorites : Favorites? = null

    open fun getFavorites(): Favorites {
        if (favorites == null) {
            favorites = SharedPrefFavorites(this)
        }
        return favorites!!
    }
}