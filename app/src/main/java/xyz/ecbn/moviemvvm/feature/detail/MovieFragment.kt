package xyz.ecbn.moviemvvm.feature.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
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
import xyz.ecbn.moviemvvm.data.model.*
import xyz.ecbn.moviemvvm.feature.PlayerActivity
import xyz.ecbn.moviemvvm.utils.show
import xyz.ecbn.moviemvvm.utils.showSnackbar
import xyz.ecbn.moviemvvm.utils.toast
import xyz.ecbn.moviemvvm.vm.DetailMovieViewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment(), TrailerAdapter.VideoSelectedListener,
    CastAdapter.CastSelectedListener, PosterAdapter.ImageSelectedListener {

    val TAG = MovieFragment::class.java.simpleName

    private val glide: RequestManager by inject()
    private val movieViewModel: DetailMovieViewModel by viewModel()
    private val trailerAdapter: TrailerAdapter by lazy {
        return@lazy TrailerAdapter(glide, this)
    }
    private val castAdapter: CastAdapter by lazy {
        return@lazy CastAdapter(glide, this)
    }
    private val posterAdapter: PosterAdapter by lazy {
        return@lazy PosterAdapter(glide, this)
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


        movieData?.let {
            initView(it)
            movieViewModel.getMovie(it.id)
            setBaseData(it)
            movieViewModel.detail(it.id).observe(viewLifecycleOwner, Observer { movie ->
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
            movieViewModel.videos(it.id).observe(viewLifecycleOwner, Observer { videos ->
                ivPlay.show(videos.isNullOrEmpty())
                tvViewTrailer.show(videos.size > 2)
                trailerAdapter.setData(videos.subList(0, if (videos.size > 2) 2 else videos.size))
            })
            movieViewModel.actors(it.id).observe(viewLifecycleOwner, Observer { actors ->
                tvViewCast.show(actors.size > 5)
                castAdapter.setActor(actors.subList(0, if (actors.size > 5) 5 else actors.size))
            })
            movieViewModel.posters(it.id).observe(viewLifecycleOwner, Observer { images ->
                tvViewPoster.show(images.size > 5)
                posterAdapter.setImage(images.subList(0, if (images.size > 5) 5 else images.size))
            })
        }

        tvRate.setOnClickListener {
            it.showSnackbar("Rate Brow")
        }
        ivPlay.setOnClickListener {
            it.showSnackbar("Play Tjuy")
        }
        tvViewCast.setOnClickListener {
            it.showSnackbar("Show More Actors")
        }
        tvViewPoster.setOnClickListener {
            it.showSnackbar("Show More Poster")
        }
        tvViewTrailer.setOnClickListener {
            it.showSnackbar("Show More Trailer")
        }
    }

    private fun initView(movie: Movie) {
        //Init Toolbar
        collapsing_toolbar.title = movie.title
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        toolbar.inflateMenu(R.menu.video_menu)
        toolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }

        //Init RecyclerView
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
        rvPoster.apply {
            adapter = posterAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            divider.addTo(this)
        }
    }

    private fun setBaseData(it: Movie) {
        glide.load(BuildConfig.BASE_URL_IMAGE.plus("w780") + it.backdropPath).into(ivDetail)
        tvMovieTitle.text = it.originalTitle.plus("(${it.releaseDate?.substring(0, 4)})")
        tvMetaScore.text = it.voteAverage.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> {
                context?.toast("Share")
            }
            R.id.menu_favorite -> {
                context?.toast("Favorite")
            }
        }
        return super.onOptionsItemSelected(item)
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

    override fun onImageSelected(view: View, actor: Image) {

    }

}
