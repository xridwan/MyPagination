package com.eve.mypagination.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.mypagination.adapter.CharacterListAdapter
import com.eve.mypagination.adapter.LoadingStateAdapter
import com.eve.mypagination.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCharacter.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData() {
        val adapter = CharacterListAdapter()
        binding.rvCharacter.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.character.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}