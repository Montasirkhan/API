package com.utils

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy


fun ImageView.load(imageUrl: String){



    this.load(imageUrl){

        placeholder(com.example.api.R.drawable.ic_launcher_foreground)
        error(com.example.api.R.drawable.noprofile)
        diskCachePolicy(CachePolicy.ENABLED)

    }
}