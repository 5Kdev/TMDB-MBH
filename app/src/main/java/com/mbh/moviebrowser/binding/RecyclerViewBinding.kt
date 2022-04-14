package com.mbh.moviebrowser.binding

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.api.response.MovieDetailResponse
import com.mbh.moviebrowser.data.Resource
import com.mbh.moviebrowser.data.Status
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.view.adapter.MovieListAdapter
import com.skydoves.baserecyclerviewadapter.BaseAdapter

@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(view: RecyclerView, adapter: BaseAdapter) {
    view.adapter = adapter

}

@BindingAdapter("adapterMovieList")
fun bindAdapterMovieList(view: RecyclerView, resource: Resource<List<Movie>>?) {
    view.bindResource(resource) {
        val adapter = view.adapter as? MovieListAdapter
        adapter?.addMovieList(it)
    }
}

@BindingAdapter("adapterGenreList")
fun bindAdapterGenreList(view: RecyclerView, resource: Resource<List<Genre>>?) {
    view.bindResource(resource) {
        val adapter = view.adapter as? MovieListAdapter
        adapter?.addGenreList(it)
    }
}

@BindingAdapter("detailTitle")
fun bindDetailTitle(view: TextView, resource: Resource<MovieDetailResponse>?) {
    view.bindResource(resource) {
        view.text= it.data!!.title
    }
}

@BindingAdapter("detailOverView")
fun bindDetailOverview(view: TextView, resource: Resource<MovieDetailResponse>?) {
    view.bindResource(resource) {
        view.text= it.data!!.title
    }
}

@BindingAdapter("detailPoster")
fun bindDetailPoster(view: ImageView, resource: Resource<MovieDetailResponse>?) {
    view.bindResource(resource) {
        it.data!!.getPoster()
    }
}


inline fun <reified T> View.bindResource(resource: Resource<T>?, onSuccess: (Resource<T>) -> Unit) {
    if (resource != null) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> onSuccess(resource)
            Status.ERROR ->
                Log.e("Error",resource.errorEnvelope?.status_message.toString())

        }
    }
}