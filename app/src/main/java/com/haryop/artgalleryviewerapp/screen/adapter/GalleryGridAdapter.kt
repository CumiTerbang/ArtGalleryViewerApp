package com.haryop.artgalleryviewerapp.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.snackbar.Snackbar
import com.haryop.artgalleryviewerapp.data.model.ArtworkItemModel
import com.haryop.artgalleryviewerapp.databinding.ItemGridGalleryBinding

class GalleryGridAdapter(
    private val context: Context
) : RecyclerView.Adapter<GalleryGridAdapter.GalleryViewHolder>() {

    private var artworksList: ArrayList<ArtworkItemModel> = ArrayList()
    var artworks: ArrayList<ArtworkItemModel>
        get() = artworksList
        set(value) {
            artworksList = value
        }

    fun addArtworks(artworks: ArrayList<ArtworkItemModel>) {
        artworksList.addAll(artworks)
    }

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
            with(artworks[position]) {
                Glide.with(context).load(getImagePath(this.imageId))
                    .placeholder(getShimmerPlaceHolder())
                    .centerCrop()
                    .into(binding.imageViewGridGaleryItem)

                itemView.setOnClickListener {
                    val snackbar = Snackbar.make(
                        itemView, "${this.title}\n${this.artistDisplay}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null)
                    snackbar.show()
                }

            }
        }
    }

    private fun getImagePath(imageId: String): String {
        return "https://www.artic.edu/iiif/2/$imageId/full/200,/0/default.jpg"
    }

    private fun getShimmerPlaceHolder(): ShimmerDrawable {
        val shimmer =
            Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.7f) //the alpha of the underlying children
                .setHighlightAlpha(0.8f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

        return ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
    }

}