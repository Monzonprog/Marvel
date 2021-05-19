package com.openbank.marvel.presentation.navigator

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MarvelNavigatorLifeCycle(app: Application) : Application.ActivityLifecycleCallbacks {
    lateinit var currentActivity: AppCompatActivity

    init {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity as AppCompatActivity
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity as AppCompatActivity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity as AppCompatActivity
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}