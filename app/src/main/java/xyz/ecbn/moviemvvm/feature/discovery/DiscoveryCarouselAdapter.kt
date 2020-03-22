package xyz.ecbn.moviemvvm.feature.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.MovieData

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class DiscoveryCarouselAdapter : RecyclerView.Adapter<DiscoveryCarouselAdapter.ViewHolder>() {

    private val items = mutableListOf<MovieData>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCarousel = itemView.findViewById<ImageView>(R.id.ivCarouosel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        Picasso.get()
            .load(BuildConfig.BASE_URL_IMAGE.plus("w780") + movie.backdropPath)
            .into(holder.ivCarousel)
    }

    fun addAll(movies: ArrayList<MovieData>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }

    fun add(movie: MovieData) {
        items.add(movie)
        notifyDataSetChanged()
    }
}