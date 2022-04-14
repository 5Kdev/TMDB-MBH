package com.mbh.moviebrowser.view.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.binding.bindRemoteImage
import com.mbh.moviebrowser.data.Resource
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie

class MovieListAdapter(private val dataSet: MutableList<Movie> = arrayListOf(),private val clickListener:((Movie) -> Unit)?) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>()  {

    private val genres: MutableList<Genre> = arrayListOf()
    var onItemClick = clickListener

    fun addGenreList(resource: Resource<List<Genre>>) {
        resource.data.let {
            if (it != null) {
                genres.clear()
                genres.addAll(it)
                notifyDataSetChanged()
            }
        }
    }

    fun addMovieList(resource: Resource<List<Movie>>) {
            resource.data?.let {
                dataSet.clear()
                dataSet.addAll(it)
                dataSet.sortByDescending { it.vote_average }
                notifyDataSetChanged()
            }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val cover: ImageView
        val genres: TextView
        val rating: ProgressBar
        val container: View

        init {
            title = view.findViewById(R.id.title)
            cover = view.findViewById(R.id.cover)
            genres = view.findViewById(R.id.genres)
            rating = view.findViewById(R.id.rating)
            container = view
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text = dataSet[position].original_title
        viewHolder.cover.bindRemoteImage("https://image.tmdb.org/t/p/w342"+dataSet[position].coverUrl)


        var moviGenres = genres.filter { dataSet[position].genreIds.contains(it.id.toInt()) } as MutableList<Genre>
        viewHolder.genres.text = moviGenres.map { it.name }.joinToString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            viewHolder.rating.min = 0
            viewHolder.rating.max = 100
        }
        viewHolder.rating.progress = (dataSet[position].vote_average*10).toInt()

        viewHolder.title.setOnClickListener {
            onItemClick?.invoke(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size

}

