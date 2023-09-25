package com.haryop.artgalleryviewerapp.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

    fun addArtworks(artworks: ArrayList<ArtworkItemModel>){
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

//    override fun getCount(): Int {
//        return artworks.size
//    }
//
//    override fun getItem(position: Int): Any? {
//        return artworks[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        if (count == 0) return convertView
//        var convertViewResult = convertView
//        if (layoutInflater == null) {
//            layoutInflater =
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        }
//
//        if (convertViewResult == null) {
//            convertViewResult = layoutInflater!!.inflate(R.layout.item_grid_gallery, null)
//        }
//
//        itemImageView = convertViewResult!!.findViewById(R.id.imageViewGridGaleryItem)
//        Glide.with(context).load(getImagePath(artworks[position].imageId)).centerCrop()
//            .into(itemImageView)
//
//        return convertViewResult
//    }

}