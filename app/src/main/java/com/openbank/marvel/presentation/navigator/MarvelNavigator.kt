package com.openbank.marvel.presentation.navigator

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

class MarvelNavigator(private val lifeCycle: MarvelNavigatorLifeCycle) {
    private val currentActivity: AppCompatActivity get() = lifeCycle.currentActivity

    fun showToast(@StringRes text: Int) {
        Toast.makeText(
            currentActivity,
            currentActivity.getString(text),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun goTo(directions: NavDirections) {
        currentActivity.supportFragmentManager.primaryNavigationFragment?.findNavController()
            ?.navigate(directions)
    }
}