package com.haryop.artgalleryviewerapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.haryop.artgalleryviewerapp.data.helper.Resource
import com.haryop.artgalleryviewerapp.data.model.ArtworkItemModel
import com.haryop.artgalleryviewerapp.databinding.ActivityMainBinding
import com.haryop.artgalleryviewerapp.screen.adapter.GalleryGridAdapter
import com.haryop.artgalleryviewerapp.screen.viewmodel.MainActivityViewModel
import com.haryop.artgalleryviewerapp.utils.EndlessRecyclerViewScrollListener
import com.haryop.artgalleryviewerapp.utils.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var galleryAdapter: GalleryGridAdapter
    private var searchKeyword: String = ""
    private var artworks: ArrayList<ArtworkItemModel> = ArrayList()

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initSearchView()
        initGalleryGridView()

        viewModel.init()
        showArtworks()
    }

    private fun initSearchView() {
        binding.searchViewSearchArt.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchKeyword = p0 ?: ""
                if (p0.isNullOrEmpty()) {
                    viewModel.init()
//                    showArtworks()
                    return false
                }
                viewModel.search(p0)
                showSearchResult()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchKeyword = p0 ?: ""
                if (p0.isNullOrEmpty()) {
                    viewModel.init()
//                    showArtworks()
                    return false
                }
                viewModel.search(p0)
                showSearchResult()
                return false
            }

        })
    }


    private fun initGalleryGridView() {
        galleryAdapter = GalleryGridAdapter(this)
        with(binding.gridViewGallery) {
            this.adapter = galleryAdapter
            val layoutManager = GridLayoutManager(baseContext, 3)
            this.layoutManager = layoutManager
            this.addItemDecoration(GridSpacingItemDecoration(3, 4, true))
            this.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(
                    currentPage: Int,
                    currentItemsCount: Int,
                    view: RecyclerView?
                ) {
                    val nextPage = currentPage + 1

                    val snackbar = Snackbar.make(
                        binding.root, "" +
                                "page = $currentPage | " +
                                "itemsCount=$currentItemsCount | " +
                                "nextPage = $nextPage",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null)
                    snackbar.show()

                    viewModel.setPage(nextPage.toString())
                }
            })
        }
    }

    private fun showArtworks() {
        viewModel.getArtworkResponse.observe(this) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    val newData = it.data?.data ?: ArrayList()
                    val pagination = it.data?.pagination

                    if (pagination == null || pagination.currentPage == 1) {
                        artworks.clear()
                        artworks = ArrayList()
                    }

                    artworks.addAll(newData)
                    galleryAdapter.artworks = artworks
                    galleryAdapter.notifyDataSetChanged()

                    binding.progressBar.visibility = View.GONE
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun showSearchResult() {
        viewModel.searchArtworkResponse.observe(this) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    val newData = it.data?.data ?: ArrayList()
                    artworks.clear()
                    artworks = ArrayList()
                    artworks.addAll(newData)

                    galleryAdapter.artworks = artworks
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