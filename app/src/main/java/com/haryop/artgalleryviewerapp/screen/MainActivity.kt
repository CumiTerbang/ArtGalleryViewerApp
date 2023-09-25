package com.haryop.artgalleryviewerapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.haryop.artgalleryviewerapp.databinding.ActivityMainBinding
import com.haryop.artgalleryviewerapp.screen.adapter.GalleryGridAdapter
import com.haryop.artgalleryviewerapp.data.model.ArtworkItemModel
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


        viewModel.getIsLoading().observe(this, Observer<Boolean>() {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                showData()
            }
        })

    }

    private fun initGalleryGridView() {
        galleryAdapter = GalleryGridAdapter(this)
        binding.gridViewGallery.adapter = galleryAdapter
    }

    private fun showData() {
        initGalleryGridView()
        viewModel.getArtworks().observe(this, Observer<List<ArtworkItemModel>>() {
            galleryAdapter.artworks = it
            galleryAdapter.notifyDataSetChanged()
        })
    }


}