package com.ashin.testapp.network

import com.ashin.testapp.model.Post
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts():List<Post>
}