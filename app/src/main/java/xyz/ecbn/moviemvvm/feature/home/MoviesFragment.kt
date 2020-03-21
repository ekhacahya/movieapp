package xyz.ecbn.moviemvvm.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { MoviesFragmentArgs.fromBundle(it) }
        val genre = args?.genre

        toolbar.title = genre?.name

        rvGenre.apply {
            layoutManager = LinearLayoutManager(this@MoviesFragment.context)
            adapter = mAdapter
        }

        movieViewModel.getMovies(genre = genre?.id.toString())
        movieViewModel.movieState.observe(
            viewLifecycleOwner, Observer {
                it.movies?.let { it1 -> mAdapter.addAll(it1) }
            }
        )
    }
}
