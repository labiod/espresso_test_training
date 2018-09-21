package com.sqisland.tutorial.recipes.test

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.sqisland.tutorial.recipes.injection.TestRecipeApplication

class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestRecipeApplication::class.java.name, context)
    }
}