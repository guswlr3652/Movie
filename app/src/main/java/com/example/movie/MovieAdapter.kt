package com.example.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

data class Movie(
    val title : String,
    val popularity : Double,
    val overview : String,
    val release_date : String,
    val poster_path : String
)

data class MovieList(
    val results: ArrayList<Movie>
)

class MovieAdapter(val context: Context, val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movieList[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val imgPoster = itemView.findViewById<ImageView>(R.id.imgPoster)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvPopularity = itemView.findViewById<TextView>(R.id.tvPopularity)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvOpenDate = itemView.findViewById<TextView>(R.id.tvOpenDate)
        val container = itemView.findViewById<ConstraintLayout>(R.id.container)

        fun bind(movie: Movie) {
            val overview: String
            if(movie.overview.length > 21) {
                overview = movie.overview.slice(IntRange(0,20)) + "..."
            } else {
                overview = movie.overview
            }
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(imgPoster)
            tvTitle.text = movie.title
            tvPopularity.text = "인기도: " + movie.popularity
            tvDescription.text = "설명: " + overview
            tvOpenDate.text = "개봉일: " + movie.release_date

            container.setOnClickListener {
                Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}