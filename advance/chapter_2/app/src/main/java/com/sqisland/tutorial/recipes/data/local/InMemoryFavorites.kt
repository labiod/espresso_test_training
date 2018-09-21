package com.sqisland.tutorial.recipes.data.local

class InMemoryFavorites: Favorites {
    private val map = HashMap<String, Boolean>()

    override fun get(id: String): Boolean {
        val result = map[id]
        return result ?: false
    }

    override fun toogle(id: String): Boolean {
        val result = get(id)
        put(id, !result)
        return !result
    }

    fun put(id: String, favorite: Boolean) {
        map[id] = favorite
    }

    fun clear() {
        map.clear()
    }
}