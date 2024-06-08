package com.ashin.testapp.Repository

import com.ashin.testapp.model.Post
import com.ashin.testapp.network.ApiServiceImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiServiceImplementation: ApiServiceImplementation) {
    fun getPost(): Flow<List<Post>> = flow {
        emit(apiServiceImplementation.getPost())
    }.flowOn(Dispatchers.IO)
}