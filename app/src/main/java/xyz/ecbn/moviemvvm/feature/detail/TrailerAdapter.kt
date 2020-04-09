package xyz.ecbn.moviemvvm.feature.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.facebook.shimmer.ShimmerFrameLayout
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Video
import xyz.ecbn.moviemvvm.utils.click
import xyz.ecbn.moviemvvm.utils.hide
import xyz.ecbn.moviemvvm.utils.show

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class TrailerAdapter(private val glide: RequestManager, selectedListener: VideoSelectedListener) :
    RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    private val items = mutableListOf<Video>()
    private val listener = selectedListener

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCarousel: ImageView = itemView.findViewById(R.id.ivCarouosel)
        val ivPlay: ImageView = itemView.findViewById(R.id.ivPlay)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = items[position]
        val imgUrl = "https://img.youtube.com/vi/${video.key}/0.jpg"
        glide.load(imgUrl).into(holder.ivCarousel)
        holder.ivPlay.show()
        holder.tvTitle.hide()
        holder.shimmer.stopShimmer()
        holder.shimmer.hideShimmer()
        holder.ivPlay.click {
            listener.onVideoSelected(video)
        }
    }

    fun setData(movies: List<Video>) {
        items.clear()
        addAll(movies)
    }

    fun addAll(movies: List<Video>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }

    fun add(movie: Video) {
        items.add(movie)
        notifyDataSetChanged()
    }

    interface VideoSelectedListener {
        fun onVideoSelected(video: Video)
    }
}