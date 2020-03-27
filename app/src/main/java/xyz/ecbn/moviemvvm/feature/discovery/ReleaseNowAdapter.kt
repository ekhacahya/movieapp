package xyz.ecbn.moviemvvm.feature.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.MovieData

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class ReleaseNowAdapter : RecyclerView.Adapter<ReleaseNowAdapter.ViewHolder>() {

    private val items = mutableListOf<MovieData>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCarousel = itemView.findViewById<ImageView>(R.id.ivMovie)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val shimmer = itemView.findViewById<ShimmerFrameLayout>(R.id.shimmer)
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
        Picasso.get()
            .load(imgUrl)
            .into(holder.ivCarousel)
        holder.tvTitle.text = movie.originalTitle
        holder.shimmer.stopShimmer()
        holder.shimmer.hideShimmer()
    }

    fun setData(movies: List<MovieData>) {
        items.clear()
        addAll(movies)
    }

    fun addAll(movies: List<MovieData>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }

    fun add(movie: MovieData) {
        items.add(movie)
        notifyDataSetChanged()
    }
}