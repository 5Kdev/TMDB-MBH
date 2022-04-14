package com.mbh.moviebrowser.api

import androidx.lifecycle.LiveData
import com.mbh.moviebrowser.api.response.TMDBResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
</R> */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<TMDBResponse<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): LiveData<TMDBResponse<R>> {
        return object : LiveData<TMDBResponse<R>>() {
            var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(TMDBResponse(response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(TMDBResponse(throwable))
                        }
                    })
                }
            }
        }
    }
}