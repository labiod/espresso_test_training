package com.sqisland.tutorial.recipes.data.model

import com.sqisland.tutorial.recipes.data.local.RecipeStore
import org.junit.Test

import org.junit.Assert.*

class RecipeTest {

    @Test
    fun water() {
        val stream = RecipeTest::class.java.getResourceAsStream("/recipes/water.txt")
        val recipe = Recipe.readFromStream(stream)
        assertNotNull(recipe)

        assertEquals("water", recipe?.id)
        assertEquals("Water", recipe?.title)
        assertEquals("Put glass under tap. Open tap. Close tap. Drink.", recipe?.description)
    }

    @Test
    fun mixed() {
        val stream = RecipeTest::class.java.getResourceAsStream("/recipes/mixed.txt")
        val recipe = Recipe.readFromStream(stream)
        assertNotNull(recipe)

        assertEquals("punch", recipe?.id)
        assertEquals("Punch", recipe?.title)
        assertEquals("Juice of 3 lemons\n" +
            "1 orange\n" +
            "1 pint grape juice\n" +
            "1 cup sugar\n" +
            "1 cup water\n" +
            "1 pine apple juice\n" +
            "Mix all together and strain. Add large piece of ice.", recipe?.description)
    }
}