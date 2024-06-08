package com.ashin.testapp.network

import com.ashin.testapp.model.Post
import javax.inject.Inject

class ApiServiceImplementation @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun getPost():List<Post> = apiInterface.getPosts()
}