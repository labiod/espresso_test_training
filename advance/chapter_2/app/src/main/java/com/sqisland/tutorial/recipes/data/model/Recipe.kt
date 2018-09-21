package com.sqisland.tutorial.recipes.data.model

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Recipe(
    val id: String,
    val title: String?,
    val description: String) {

    companion object {
        private const val ID_PREFIX = "id="
        private const val TITLE_PREFIX = "title="

        @JvmStatic
        fun readFromStream(stream: InputStream): Recipe? {
            var id:String = ""
            var title: String? = null
            val description = StringBuilder()
            val bufferedReader = BufferedReader(InputStreamReader(stream))
            var line = bufferedReader.readLine()
            try {
                while (line != null) {
                    if (line.startsWith(ID_PREFIX)) {
                        id = line.substring(ID_PREFIX.length)
                    } else if (line.startsWith(TITLE_PREFIX)) {
                        title = line.substring(TITLE_PREFIX.length)
                    } else {
                        if (description.isNotEmpty()) {
                            description.append("\n")
                        }
                        description.append(line)
                    }
                    line = bufferedReader.readLine()
                }
            } catch (e: IOException) {
                return null
            }
            return Recipe(id, title, description.toString())
        }
    }

}