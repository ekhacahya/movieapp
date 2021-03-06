package xyz.ecbn.moviemvvm.feature.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Genre

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val items = mutableListOf<Genre>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        /*val imgUrl = BuildConfig.BASE_URL_IMAGE.plus("w154") + movie.posterPath
        Picasso.get()
            .load(imgUrl)
            .into(holder.ivCarousel)*/
        holder.tvTitle.text = movie.name
        holder.shimmer.stopShimmer()
        holder.shimmer.hideShimmer()
    }

    fun setData(movies: List<Genre>) {
        items.clear()
        addAll(movies)
    }

    fun addAll(movies: List<Genre>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }

    fun add(movie: Genre) {
        items.add(movie)
        notifyDataSetChanged()
    }
}