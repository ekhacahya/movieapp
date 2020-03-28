package xyz.ecbn.moviemvvm.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Genre
import xyz.ecbn.moviemvvm.utils.ItemSelectedListener

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class GenreAdapter(selectedListener: ItemSelectedListener) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val items = mutableListOf<Genre>()
    private val listener = selectedListener

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
        val genre = items[holder.adapterPosition]
        holder.tvTitle.text = genre.name
        holder.cardView.setOnClickListener {
            val movies = HomeFragmentDirections.movies(genre)
            it.findNavController().navigate(movies)
        }

    }

    fun addAll(movies: ArrayList<Genre>) {
        movies.mapIndexed { index, genre ->
            if (index < 3) items.add(genre)
        }
//        items.addAll(movies)
        notifyDataSetChanged()
    }
}