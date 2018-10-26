package com.sqisland.tutorial.recipes.ui.recipe

import com.sqisland.tutorial.recipes.data.local.Favorites
import com.sqisland.tutorial.recipes.data.local.RecipeStore
import com.sqisland.tutorial.recipes.data.model.Recipe
import com.sqisland.tutorial.recipes.data.model.RecipeTest
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import java.lang.Exception
import java.lang.IllegalStateException

class RecipePresenterTest {
    private lateinit var store: RecipeStore;
    private lateinit var favorites: Favorites;
    private lateinit var view: RecipeContract.View;
    private lateinit var presenter: RecipePresenter;

    @Before
    fun setup() {
        store = mock(RecipeStore::class.java)
        favorites = mock(Favorites::class.java)
        view = mock(RecipeContract.View::class.java)
        presenter = RecipePresenter(store, view, favorites)
    }

    @Test
    fun recipeNotFound() {
        `when`(store.getRecipe(anyString())).thenReturn(null)
        presenter.loadRecipe("no_such_recipe")
        verify(view, times(1)).showRecipeNotFoundError()
    }

    @Test(expected = IllegalStateException::class)
    fun toggleWithoutLoad() {
        presenter.toggleFavorite()
    }

    @Test
    fun loadWaterAndFavorite() {
        val stream = RecipePresenterTest::class.java.getResourceAsStream("/recipes/water.txt")
        val recipe = Recipe.readFromStream(stream)
        `when`(store.getRecipe(anyString())).thenReturn(recipe)
        `when`(favorites.toogle(anyString())).thenReturn(true)

        presenter.loadRecipe("water")
        presenter.toggleFavorite()

        val captor: ArgumentCaptor<Boolean> = ArgumentCaptor.forClass(Boolean::class.java)
        verify(view, times(2)).setFavorite(captor.capture())
        assertFalse(captor.allValues[0])
        assertTrue(captor.allValues[1])
    }
}