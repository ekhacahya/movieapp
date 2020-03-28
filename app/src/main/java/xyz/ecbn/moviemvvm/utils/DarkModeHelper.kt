package xyz.ecbn.moviemvvm.utils

import androidx.appcompat.app.AppCompatDelegate

/**
 * MovieAppMVVM Created by ecbn on 24/03/20.
 */
internal class DarkModeHelper {
    internal fun onModeChanged(newMode: Boolean, delegate: AppCompatDelegate) {
        if (newMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        delegate.applyDayNight()
    }
}