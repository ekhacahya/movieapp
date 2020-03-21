package xyz.ecbn.moviemvvm.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.MovieData

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val items = mutableListOf<MovieData>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.textView)
        val cardView = itemView.findViewById<CardView>(R.id.cardParent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[holder.adapterPosition]
        holder.tvTitle.text = movie.originalTitle
        holder.cardView.setOnClickListener {
            val mv = MoviesFragmentDirections.actionMoviesFragmentToMovieFragment()
            mv.movie = movie
            it.findNavController().navigate(mv)
        }
    }

    fun addAll(movies: ArrayList<MovieData>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }
}