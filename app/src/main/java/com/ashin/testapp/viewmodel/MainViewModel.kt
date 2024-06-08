package com.ashin.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashin.testapp.Repository.MainRepository
import com.ashin.testapp.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {
      private val postStateFlow:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
      val _postStateFlow:StateFlow<ApiState> = postStateFlow


    fun getPost()=viewModelScope.launch {
        postStateFlow.value=ApiState.Loading
        repository.getPost().catch {
            ae->
            postStateFlow.value=ApiState.Failure(ae)
        }.collect{
            data->postStateFlow.value=ApiState.Success(data)
        }
    }
}