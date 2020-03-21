package xyz.ecbn.moviemvvm.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.utils.ItemSelectedListener
import xyz.ecbn.moviemvvm.vm.MovieViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), ItemSelectedListener {

    private val TAG = HomeFragment::class.java.simpleName
    private val movieViewModel: MovieViewModel by viewModel()
    private val mAdapter: GenreAdapter by lazy {
        return@lazy GenreAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.app_name)

        rvGenre.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = mAdapter
        }

        if (mAdapter.itemCount < 1) {
            movieViewModel.getGenres()
            movieViewModel.genreState.observe(
                viewLifecycleOwner, Observer {
                    mAdapter.addAll(it.genres)
                }
            )
        }
    }

    override fun onItemClicked(v: Any) {

    }
}
