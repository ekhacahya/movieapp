package xyz.ecbn.moviemvvm.feature.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yarolegovich.discretescrollview.DSVOrientation
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.discovery_content.*
import kotlinx.android.synthetic.main.fragment_discovery.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.base.BaseFragment
import xyz.ecbn.moviemvvm.vm.MovieViewModel


/**
 * A simple [Fragment] subclass.
 */
class DiscoveryFragment : BaseFragment() {
    val TAG = DiscoveryFragment::class.java.simpleName

    private val movieViewModel: MovieViewModel by sharedViewModel()
    private val releaseNowAdapter: ReleaseNowAdapter by lazy {
        val a = ReleaseNowAdapter()
        a.setHasStableIds(true)
        return@lazy a
    }
    private val discoveryCarouselAdapter: DiscoveryCarouselAdapter by lazy {
        return@lazy DiscoveryCarouselAdapter()
    }
    private val mAdapterDiscovery: InfiniteScrollAdapter<DiscoveryCarouselAdapter.ViewHolder> by lazy {
        return@lazy InfiniteScrollAdapter.wrap(discoveryCarouselAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            movieViewModel.getGenres()
            movieViewModel.getMovies()
        }
        initRecyclerView()
        movieViewModel.getMovies()
        movieViewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let { it1 ->
                discoveryCarouselAdapter.addAll(it1)
                releaseNowAdapter.setData(it1)
            }
        })
        movieViewModel.networkState.observe(viewLifecycleOwner, observer)
    }

    private fun initRecyclerView() {
        rvReleaseNow.apply {
            adapter = releaseNowAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvCarousel.apply {
            adapter = mAdapterDiscovery
            setOrientation(DSVOrientation.HORIZONTAL)
            setItemTransitionTimeMillis(150)
            setItemTransformer(
                ScaleTransformer.Builder()
                    .setMinScale(0.8f)
                    .build()
            )
        }
    }

    override fun showErrorMessage(msg: String) {
        showSnackBar(parent, msg)
    }

    override fun showProgressBar() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgressBar() {
        swipeRefreshLayout.isRefreshing = false
    }
}
