package xyz.ecbn.moviemvvm.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import xyz.ecbn.moviemvvm.data.NetworkState
import xyz.ecbn.moviemvvm.data.Status

/**
 * MovieAppMVVM Created by ecbn on 26/03/20.
 */
abstract class BaseFragment : Fragment() {

    open fun showErrorMessage(msg: String) {

    }

    open fun showSnackBar(view: View, msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, msg, duration).show()
    }

    open fun showSnackBar(view: View, msg: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, msg, duration).show()
    }

    open fun showProgressBar() {

    }

    open fun hideProgressBar() {

    }

    val observer = Observer<NetworkState> {
        when (it.status) {
            Status.RUNNING -> {
                showProgressBar()
            }
            Status.FAILED -> {
                hideProgressBar()
                showErrorMessage(it.msg)
            }
            Status.SUCCESS -> {
                hideProgressBar()
            }
        }
    }
}
