package xyz.ecbn.moviemvvm.feature.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.facebook.shimmer.ShimmerFrameLayout
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Image
import xyz.ecbn.moviemvvm.utils.click
import xyz.ecbn.moviemvvm.utils.hide

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class PosterAdapter(private val glide: RequestManager, selectedListener: ImageSelectedListener) :
    RecyclerView.Adapter<PosterAdapter.ViewHolder>() {

    private val items = mutableListOf<Image>()
    private val listener = selectedListener

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
        val image = items[position]
        val imgUrl = BuildConfig.BASE_URL_IMAGE.plus("w154") + image.filePath
        glide.load(imgUrl).into(holder.ivCarousel)
        holder.tvTitle.hide()
        holder.shimmer.stopShimmer()
        holder.shimmer.hideShimmer()
        holder.shimmer.click {
            listener.onImageSelected(it, image)
        }
    }

    fun setImage(images: List<Image>) {
        items.clear()
        addImages(images)
    }

    fun addImages(images: List<Image>) {
        items.addAll(images)
        notifyDataSetChanged()
    }

    fun add(image: Image) {
        items.add(image)
        notifyDataSetChanged()
    }

    interface ImageSelectedListener {
        fun onImageSelected(view: View, image: Image)
    }
}