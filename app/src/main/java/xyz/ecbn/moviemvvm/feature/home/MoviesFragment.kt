package xyz.ecbn.moviemvvm.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.vm.MovieViewModel

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {

    private val TAG = MoviesFragment::class.java.simpleName
    private val movieViewModel: MovieViewModel by sharedViewModel()
    private val mAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}
