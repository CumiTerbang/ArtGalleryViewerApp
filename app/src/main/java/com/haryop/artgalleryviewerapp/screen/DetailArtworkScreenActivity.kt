package com.haryop.artgalleryviewerapp.screen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.haryop.artgalleryviewerapp.data.helper.Constants
import com.haryop.artgalleryviewerapp.data.model.ArtworkItemModel
import com.haryop.artgalleryviewerapp.databinding.ActivityDetailArtScreenBinding
import com.haryop.artgalleryviewerapp.utils.CustomShimmerPlaceholder

class DetailArtworkScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArtScreenBinding
    private lateinit var artworkItemModel: ArtworkItemModel
    private var isShowDescription = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        parseIntentExtras()
        initViews()
    }

    private fun parseIntentExtras() {
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("artworkItemModel")
            artworkItemModel = Gson().fromJson(value, ArtworkItemModel::class.java)
        }
    }

    private fun initViews() = with(binding) {
        Glide.with(this@DetailArtworkScreenActivity)
            .load(Constants.getImagePath(artworkItemModel.imageId))
            .placeholder(CustomShimmerPlaceholder().getPlaceholder())
            .into(imageViewArtwork)

        textViewDescription.text =
            "${artworkItemModel.title} (${artworkItemModel.dateDisplay})\n" +
                    "${artworkItemModel.artistDisplay}"

        imageViewCloseButton.setOnClickListener {
            finish()
        }

        imageViewArtwork.setOnClickListener {
            if (isShowDescription) {
                imageViewCloseButton.visibility = View.GONE
                textViewDescription.visibility = View.GONE
                isShowDescription = false

                return@setOnClickListener
            }

            imageViewCloseButton.visibility = View.VISIBLE
            textViewDescription.visibility = View.VISIBLE
            isShowDescription = true

        }

    }
}