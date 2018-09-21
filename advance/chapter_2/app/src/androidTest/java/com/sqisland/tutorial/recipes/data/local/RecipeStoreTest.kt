package com.sqisland.tutorial.recipes.data.local

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.sqisland.tutorial.recipes.data.model.Recipe
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeStoreTest {
    @Test
    fun nullDirection() {
        val context = InstrumentationRegistry.getTargetContext()
        val store = RecipeStore(context, null)
        assertNotNull(store)
        assertNotNull(store.recipes)
        assertEquals(0, store.recipes.size)
    }

    @Test
    fun count() {
        val context = InstrumentationRegistry.getTargetContext()
        val store = RecipeStore(context, "recipes")
        assertNotNull(store)
        assertNotNull(store.recipes)
        assertEquals(4, store.recipes.size)
    }

    @Test
    fun getChocolatePudding() {
        val context = InstrumentationRegistry.getTargetContext()
        val store = RecipeStore(context, "recipes")
        val recipe: Recipe? = store.getRecipe("chocolate_pudding")
        assertNotNull(recipe)
        assertEquals("chocolate_pudding", recipe!!.id)
        assertEquals("Chocolate Pudding", recipe.title)
    }
}
