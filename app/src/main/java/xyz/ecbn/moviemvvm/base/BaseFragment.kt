package xyz.ecbn.moviemvvm.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import xyz.ecbn.moviemvvm.data.NetworkState
import xyz.ecbn.moviemvvm.data.Status

/**
 * MovieAppMVVM Created by ecbn on 26/03/20.
 */
abstract class BaseFragment : Fragment() {

    open fun showErrorMessage(msg: String) {

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
