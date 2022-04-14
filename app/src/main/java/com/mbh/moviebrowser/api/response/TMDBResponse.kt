package com.mbh.moviebrowser.api.response

import okio.IOException
import retrofit2.Response
import timber.log.Timber

class TMDBResponse<T> {
    val httpStatuscode: Int
    val body: T?
    val message: String?

    val isSuccessful: Boolean
        get() = httpStatuscode in 200..300
    val isFailure: Boolean

    constructor(error: Throwable) {
        this.httpStatuscode = 500
        this.body = null
        this.message = error.message
        this.isFailure = true
    }

    constructor(response: Response<T>) {
        this.httpStatuscode = response.code()

        if (response.isSuccessful) {
            this.body = response.body()
            this.message = null
            this.isFailure = false
        } else {
            var errorMessage: String? = null
            response.errorBody()?.let {
                try {
                    errorMessage = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                    Timber.e(ignored, "error while parsing response")
                }
            }

            errorMessage?.apply {
                if (isNullOrEmpty() || trim { it <= ' ' }.isEmpty()) {
                    errorMessage = response.message()
                }
            }

            this.body = null
            this.message = errorMessage
            this.isFailure = true
        }
    }
}