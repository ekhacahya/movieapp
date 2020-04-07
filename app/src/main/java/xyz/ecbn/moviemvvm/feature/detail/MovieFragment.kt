package xyz.ecbn.moviemvvm.feature.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.layout_header_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.base.BaseFragment
import xyz.ecbn.moviemvvm.data.model.Movie
import xyz.ecbn.moviemvvm.utils.showSnackbar

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment() {

    val TAG = MovieFragment::class.java.simpleName

    private val glide: RequestManager by inject()
    private val movieViewModel: DetailMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { MovieFragmentArgs.fromBundle(it) }
        val movieData = args?.movie

        movieData?.let {
            movieViewModel.getMovie(it.id!!)
            setBaseData(it)
            movieViewModel.detail(it.id!!).observe(viewLifecycleOwner, Observer { movie ->
                Log.d(TAG, "SIZE :voteAverage ${movie.title}")
                if (movie != null) {
                    tvMetaScore.text = movie.voteAverage.toString()
                    tvMovieLength.text = "${movie.runtime}${getString(R.string.detail_length)}"
                    if (!movie.genres.isNullOrEmpty()) {
                        var gen = ""
                        movie.genres?.map { genre ->
                            gen += "#${genre.name} "
                        }
                        tvMovieGenre.text = gen
                    }
                }
            })
        }


        tvRate.setOnClickListener {
            it.showSnackbar("Rate Brow")
        }
        ivPlay.setOnClickListener {
            it.showSnackbar("Play Tjuy")
        }
    }

    private fun setBaseData(it: Movie) {
        glide.load(BuildConfig.BASE_URL_IMAGE.plus("w780") + it.backdropPath).into(ivDetail)
        tvMovieTitle.text = it.originalTitle
        tvMetaScore.text = it.voteAverage.toString()
    }

}
