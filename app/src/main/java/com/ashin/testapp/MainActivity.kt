package com.ashin.testapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashin.testapp.adapter.PostAdapter
import com.ashin.testapp.databinding.ActivityMainBinding
import com.ashin.testapp.model.Post
import com.ashin.testapp.util.ApiState
import com.ashin.testapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val activity=this
    private lateinit var binding:ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private var list:ArrayList<Post> = ArrayList()
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        postAdapter= PostAdapter(list)
      binding.recyclerview.apply {
          setHasFixedSize(true)
          layoutManager= LinearLayoutManager(activity)
          adapter=postAdapter
      }
        viewModel.getPost()
        lifecycleScope.launch {
            viewModel._postStateFlow.collect{
                when(it)
                {
                    is ApiState.Empty -> {

                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=false
                    }
                    is ApiState.Loading -> {
                       binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=true
                        Log.d("loading","Loading")
                    }
                    is ApiState.Success -> {
                        binding.recyclerview.isVisible=true
                        binding.progressBar.isVisible=false
                        list.addAll(it.data)
                        postAdapter.notifyDataSetChanged()
                        Log.d("loading","Loading${it.data}")
                    }
                }
            }
        }

    }
}