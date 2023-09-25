package com.haryop.artgalleryviewerapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.haryop.artgalleryviewerapp.data.helper.Resource
import com.haryop.artgalleryviewerapp.databinding.ActivityMainBinding
import com.haryop.artgalleryviewerapp.screen.adapter.GalleryGridAdapter
import com.haryop.artgalleryviewerapp.screen.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var galleryAdapter: GalleryGridAdapter

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.init()
        initGalleryGridView()
        showData()
    }

    private fun initGalleryGridView() {
        galleryAdapter = GalleryGridAdapter(this)
        binding.gridViewGallery.adapter = galleryAdapter
    }

    private fun showData() {
        viewModel.getArtworkResponse.observe(this) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    galleryAdapter.artworks = it.data?.data ?: ArrayList()
                    galleryAdapter.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }


}