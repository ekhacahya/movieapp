package xyz.ecbn.moviemvvm.feature.discovery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.discovery_content.*
import kotlinx.android.synthetic.main.fragment_discovery.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.base.BaseFragment
import xyz.ecbn.moviemvvm.utils.showSnackbar
import xyz.ecbn.moviemvvm.vm.MovieViewModel

/**
 * A simple [Fragment] subclass.
 */
class DiscoveryFragment : BaseFragment() {
    val TAG = DiscoveryFragment::class.java.simpleName

    private val glide: RequestManager by inject()

    private val movieViewModel: MovieViewModel by viewModel()
    private val releaseNowAdapter: MovieVerticalAdapter by lazy {
        return@lazy MovieVerticalAdapter(glide)
    }
    private val nowPlayingAdapter: MovieVerticalAdapter by lazy {
        return@lazy MovieVerticalAdapter(glide)
    }
    private val genreAdapter: GenreAdapter by lazy {
        return@lazy GenreAdapter()
    }
    private val carouselAdapter: DiscoveryCarouselAdapter by lazy {
        return@lazy DiscoveryCarouselAdapter(glide)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        swipeRefreshLayout.setOnRefreshListener {
            movieViewModel.getGenres()
            movieViewModel.getMovies()
        }
        initRecyclerView()
        movieViewModel.movies.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "movieViewModel.movies: ")
            carouselAdapter.setData(it)
            releaseNowAdapter.setData(it)
        })
        movieViewModel.genres.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "movieViewModel.genres: ")
            genreAdapter.setData(it)
        })
        movieViewModel.nowPlaying.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "movieViewModel.nowPlaying: ")
            nowPlayingAdapter.setData(it)
        })
        movieViewModel.network.observe(viewLifecycleOwner, observer)
    }

    private fun initRecyclerView() {
        val cardWidthPixels = requireActivity().resources.displayMetrics.widthPixels.times(0.80f)
        val cardHintPercent = 0.01f
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvCarousel)

        rvReleaseNow.apply {
            adapter = releaseNowAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvNowPlaying.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvGenre.apply {
            adapter = genreAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvCarousel.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = carouselAdapter
            addItemDecoration(
                CarouselDecoration(
                    requireActivity(),
                    cardWidthPixels.toInt(),
                    cardHintPercent
                )
            )
        }
    }

    override fun showErrorMessage(msg: String) {
        parent.showSnackbar(msg)
    }

    override fun showProgressBar() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgressBar() {
        swipeRefreshLayout.isRefreshing = false
    }
}
