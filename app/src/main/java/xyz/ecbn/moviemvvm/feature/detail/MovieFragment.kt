package xyz.ecbn.moviemvvm.feature.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.layout_header_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.base.BaseFragment
import xyz.ecbn.moviemvvm.data.model.Actor
import xyz.ecbn.moviemvvm.data.model.Crew
import xyz.ecbn.moviemvvm.data.model.Movie
import xyz.ecbn.moviemvvm.data.model.Video
import xyz.ecbn.moviemvvm.feature.PlayerActivity
import xyz.ecbn.moviemvvm.utils.hide
import xyz.ecbn.moviemvvm.utils.show
import xyz.ecbn.moviemvvm.utils.showSnackbar

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment(), TrailerAdapter.VideoSelectedListener,
    CastAdapter.CastSelectedListener {

    val TAG = MovieFragment::class.java.simpleName

    private val glide: RequestManager by inject()
    private val movieViewModel: DetailMovieViewModel by viewModel()
    private val trailerAdapter: TrailerAdapter by lazy {
        return@lazy TrailerAdapter(glide, this)
    }
    private val castAdapter: CastAdapter by lazy {
        return@lazy CastAdapter(glide, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { MovieFragmentArgs.fromBundle(it) }
        val movieData = args?.movie

        initView()

        movieData?.let {
            movieViewModel.getMovie(it.id!!)
            setBaseData(it)
            movieViewModel.detail(it.id!!).observe(viewLifecycleOwner, Observer { movie ->
                Log.d(TAG, "SIZE :voteAverage ${movie.videos}")
                if (movie != null) {
                    tvMetaScore.text = movie.voteAverage.toString()
                    tvMovieLength.text = "${movie.runtime}${getString(R.string.detail_length)}"
                    tvStoryline.text = movie.overview

                    if (!movie.genres.isNullOrEmpty()) {
                        var gen = ""
                        movie.genres?.map { genre ->
                            gen += "#${genre.name} "
                        }
                        tvMovieGenre.text = gen
                    }
                }
            })
            movieViewModel.videos(it.id!!).observe(viewLifecycleOwner, Observer { videos ->
                if (videos.isNullOrEmpty()) ivPlay.hide() else ivPlay.show()
                trailerAdapter.setData(videos)
            })
            movieViewModel.actors(it.id!!).observe(viewLifecycleOwner, Observer { actors ->
                if (actors.isNullOrEmpty()) ivPlay.hide() else ivPlay.show()
                castAdapter.setActor(actors)
            })
        }

        tvRate.setOnClickListener {
            it.showSnackbar("Rate Brow")
        }
        ivPlay.setOnClickListener {
            it.showSnackbar("Play Tjuy")
        }
    }

    private fun initView() {
        val divider = RecyclerViewDivider.with(rvVideos.context)
            .asSpace()
            .size(8)
            .build()

        rvVideos.apply {
            adapter = trailerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            divider.addTo(this)
        }
        rvCast.apply {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            divider.addTo(this)
        }
    }

    private fun setBaseData(it: Movie) {
        glide.load(BuildConfig.BASE_URL_IMAGE.plus("w780") + it.backdropPath).into(ivDetail)
        tvMovieTitle.text = it.originalTitle
        tvMetaScore.text = it.voteAverage.toString()
    }

    override fun onVideoSelected(video: Video) {
        val intent = Intent(context, PlayerActivity::class.java)
        intent.putExtra("url", video.key)
        startActivity(intent)
    }

    override fun onActorSelected(view: View, actor: Actor) {
        view.showSnackbar(actor.name.toString())
    }

    override fun onCrewSelected(view: View, crew: Crew) {

    }

}
