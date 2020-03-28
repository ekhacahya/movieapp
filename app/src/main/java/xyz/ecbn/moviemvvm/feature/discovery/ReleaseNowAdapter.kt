package xyz.ecbn.moviemvvm.feature.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.facebook.shimmer.ShimmerFrameLayout
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Movie

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class ReleaseNowAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<ReleaseNowAdapter.ViewHolder>() {

    private val items = mutableListOf<Movie>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCarousel: ImageView = itemView.findViewById<ImageView>(R.id.ivMovie)
        val tvTitle: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        val shimmer: ShimmerFrameLayout = itemView.findViewById<ShimmerFrameLayout>(R.id.shimmer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return items[position].id!!.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        val imgUrl = BuildConfig.BASE_URL_IMAGE.plus("w154") + movie.posterPath
        glide.load(imgUrl).into(holder.ivCarousel)
        holder.tvTitle.text = movie.originalTitle
        holder.shimmer.stopShimmer()
        holder.shimmer.hideShimmer()
        holder.shimmer.setOnClickListener {
            val mv =
                DiscoveryFragmentDirections.actionDiscoveryFragmentToMovieFragment2(movie)
            it.findNavController().navigate(mv)
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