package xyz.ecbn.moviemvvm.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Movie

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val items = mutableListOf<Movie>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById<TextView>(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[holder.adapterPosition]
        holder.tvTitle.text = movie.originalTitle
    }

    fun addAll(movies: ArrayList<Movie>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }
}