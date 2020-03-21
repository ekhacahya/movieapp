package xyz.ecbn.moviemvvm

import android.os.Bundle
import xyz.ecbn.moviemvvm.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
