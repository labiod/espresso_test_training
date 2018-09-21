package com.sqisland.tutorial.recipes.data.local

import android.content.Context
import android.content.res.AssetManager
import com.sqisland.tutorial.recipes.data.model.Recipe
import java.io.File
import java.io.IOException
import java.io.InputStream

class RecipeStore(context: Context, directory: String?) {
    val recipes: ArrayList<Recipe> = ArrayList()
    val map = HashMap<String, Recipe>()

    init {
        val streams = getAssetStream(context.assets, directory)
        streams.forEach { stream ->
            val recipe = Recipe.readFromStream(stream)
            recipe?.let {
                recipes.add(it)
                map.put(it.id, it)
            }
        }
    }

    fun getRecipe(id: String?): Recipe? {
        return map[id]
    }

    companion object {
        private fun getAssetStream(manager: AssetManager, directory: String?): List<InputStream> {
            val filenames = getFilenames(manager, directory)
            return filenames.map {
                manager.open(File(directory, it).path)
            }
        }

        private fun getFilenames(manager: AssetManager, directory: String?): Array<String> {
            if (directory == null) {
                return arrayOf()
            }
            try {
                return manager.list(directory)
            } catch (e: IOException) {
                return arrayOf()
            }
        }
    }
}