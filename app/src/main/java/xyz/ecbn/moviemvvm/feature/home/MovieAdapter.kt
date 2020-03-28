package xyz.ecbn.moviemvvm.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Movie

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val items = mutableListOf<Movie>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.textView)
        val cardView: CardView = itemView.findViewById(R.id.cardParent)
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
            Navigation.findNavController(it).navigate(mv)
        }
    }

    fun addAll(movies: ArrayList<Movie>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }
}