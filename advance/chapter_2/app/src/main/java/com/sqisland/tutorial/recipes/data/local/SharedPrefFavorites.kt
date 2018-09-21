package com.sqisland.tutorial.recipes.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefFavorites(context: Context) : Favorites {
    val pref: SharedPreferences = context.getSharedPreferences("favorite.xml", Context.MODE_PRIVATE)

    override fun get(id: String): Boolean {
        return pref.getBoolean(id, false)
    }

    fun put(id: String, favorite: Boolean) {
        val editor = pref.edit()
        if (favorite) {
            editor.putBoolean(id, favorite)
        } else {
            editor.remove(id)
        }
        editor.apply()
    }

    override fun toogle(id: String): Boolean {
        val favorite = get(id)
        put(id, !favorite)
        return !favorite
    }
}