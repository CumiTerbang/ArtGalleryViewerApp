package com.haryop.artgalleryviewerapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
public class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }

}