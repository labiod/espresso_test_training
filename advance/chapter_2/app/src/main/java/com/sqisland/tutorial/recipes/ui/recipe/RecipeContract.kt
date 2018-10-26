package com.sqisland.tutorial.recipes.ui.recipe

interface RecipeContract {
    interface View {
        fun showRecipeNotFoundError()
        fun setTitle(title: String?)
        fun setDescription(desc: String?)
        fun setFavorite(isFav: Boolean)
    }
    interface Listener
}