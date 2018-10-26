package com.sqisland.android.espresso.idling

import android.support.test.espresso.IdlingResource
import android.support.v4.app.FragmentManager

class DialogFragmentIdlingResource(private val manager: FragmentManager, private val tag: String) : IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName() = DialogFragmentIdlingResource::class.java.name + ":" + tag

    override fun isIdleNow(): Boolean {
        val idle = manager.findFragmentByTag(tag) == null
        if (idle)
            callback?.onTransitionToIdle()
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}