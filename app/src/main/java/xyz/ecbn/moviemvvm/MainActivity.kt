package xyz.ecbn.moviemvvm

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import org.koin.android.ext.android.inject
import xyz.ecbn.moviemvvm.base.BaseActivity
import xyz.ecbn.moviemvvm.utils.DarkModeHelper

class MainActivity : BaseActivity() {

    private val darkModeHelper: DarkModeHelper by inject()
    private var darkMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            configureAutoDarkMode()
        } else {
            darkModeHelper.onModeChanged(darkMode, delegate)
        }
//        darkModeHelper.onModeChanged(true, delegate)
    }

    private fun configureAutoDarkMode() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> darkMode = true
            Configuration.UI_MODE_NIGHT_NO -> darkMode = false
        }
    }
}
