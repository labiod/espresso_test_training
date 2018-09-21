package com.sqisland.tutorial.recipes.ui.main

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sqisland.tutorial.recipes.R
import com.sqisland.tutorial.recipes.data.local.RecipeStore
import com.sqisland.tutorial.recipes.ui.recipe.RecipeActivity

class RecipeAdapter(val store: RecipeStore) : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = store.recipes[position]
        holder.textView.text = recipe.title
        holder.textView.setOnClickListener {
            val context = holder.textView.context
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra(RecipeActivity.KEY_ID, recipe.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return store.recipes.size
    }
}