package com.ubaya.a160419162_muliahati.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419162_muliahati.R
import java.lang.Exception

fun ImageView.loadImage(url: String?){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .into(this, object : Callback {
            override fun onSuccess() {
            }
            override fun onError(e: Exception?) {
            }

        })

}
fun ImageView.loadImage(url: String?,width:Int,height:Int){
    Picasso.get()
        .load(url)
        .resize(width,height)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback{
            override fun onSuccess() {
            }
            override fun onError(e: Exception?) {
            }
        })

}
fun ImageView.loadImageDonasi(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback{
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                TODO("Not yet implemented")
            }

        })

}