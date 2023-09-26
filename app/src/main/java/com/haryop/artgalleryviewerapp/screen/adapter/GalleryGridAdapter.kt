package com.haryop.artgalleryviewerapp.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haryop.artgalleryviewerapp.data.helper.Constants
import com.haryop.artgalleryviewerapp.data.model.ArtworkItemModel
import com.haryop.artgalleryviewerapp.databinding.ItemGridGalleryBinding
import com.haryop.artgalleryviewerapp.utils.CustomShimmerPlaceholder

class GalleryGridAdapter(
    private val context: Context
) : RecyclerView.Adapter<GalleryGridAdapter.GalleryViewHolder>() {

    private var artworksList: ArrayList<ArtworkItemModel> = ArrayList()
    var artworks: ArrayList<ArtworkItemModel>
        get() = artworksList
        set(value) {
            artworksList = value
        }

    var onItemClick: ((ArtworkItemModel) -> Unit)? = null

    inner class GalleryViewHolder(val binding: ItemGridGalleryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding =
            ItemGridGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return artworks.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        with(holder) {

            if (artworks.isNullOrEmpty() || position > (artworks.size-1)) return

            with(artworks[position]) {
                Glide.with(context).load(Constants.getImagePath(this.imageId))
                    .placeholder(CustomShimmerPlaceholder().getPlaceholder())
                    .centerCrop()
                    .into(binding.imageViewGridGaleryItem)

                itemView.setOnClickListener {
                    onItemClick?.invoke(this)
                }

            }
        }
    }

}