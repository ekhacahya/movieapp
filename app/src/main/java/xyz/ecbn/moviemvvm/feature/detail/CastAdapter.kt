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
import xyz.ecbn.moviemvvm.data.model.Actor
import xyz.ecbn.moviemvvm.data.model.Crew
import xyz.ecbn.moviemvvm.utils.click
import xyz.ecbn.moviemvvm.utils.hide

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class CastAdapter(private val glide: RequestManager, selectedListener: CastSelectedListener) :
    RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val items = mutableListOf<Actor>()
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
        val actor = items[position]
        val imgUrl = BuildConfig.BASE_URL_IMAGE.plus("w154") + actor.profilePath
        glide.load(imgUrl).into(holder.ivCarousel)
        holder.tvTitle.hide()
        holder.shimmer.stopShimmer()
        holder.shimmer.hideShimmer()
        holder.shimmer.click {
            listener.onActorSelected(it, actor)
        }
    }

    fun setActor(actors: List<Actor>) {
        items.clear()
        addActors(actors)
    }

    fun addActors(actors: List<Actor>) {
        items.addAll(actors)
        notifyDataSetChanged()
    }

    fun add(actor: Actor) {
        items.add(actor)
        notifyDataSetChanged()
    }

    interface CastSelectedListener {
        fun onActorSelected(view: View, actor: Actor)
        fun onCrewSelected(view: View, crew: Crew)
    }
}