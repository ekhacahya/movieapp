package xyz.ecbn.moviemvvm.feature.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.facebook.shimmer.ShimmerFrameLayout
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Movie
import xyz.ecbn.moviemvvm.utils.click

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class MovieVerticalAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<MovieVerticalAdapter.ViewHolder>() {

    private val items = mutableListOf<Movie>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCarousel: ImageView = itemView.findViewById(R.id.ivMovie)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        val imgUrl = BuildConfig.BASE_URL_IMAGE.plus("w154") + movie.posterPath
        glide.load(imgUrl).into(holder.ivCarousel)
        holder.tvTitle.text = movie.originalTitle
        holder.shimmer.stopShimmer()
        holder.shimmer.hideShimmer()
        holder.shimmer.click {
            /*val mv = DiscoveryFragmentDirections.actionDiscoveryFragmentToMovieFragment3(movie)
            it.findNavController().navigate(mv)*/
            val bundle = bundleOf("movie" to items[position])
            it.findNavController().navigate(
                R.id.action_discovery_to_movie,
                bundle
            )
        }
    }

    fun setData(movies: List<Movie>) {
        items.clear()
        addAll(movies)
    }

    fun addAll(movies: List<Movie>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }

    fun add(movie: Movie) {
        items.add(movie)
        notifyDataSetChanged()
    }
}