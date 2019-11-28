package com.example.accounttest.model

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

data class TransactionHistoryItem(
    var title: String = "",
    var icon_url: String = "",
    var date: String = "",
    var amount: String = "",
    var changedAmount: Double = 0.0,
    var currencySign: String = ""
) {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["profileImage", "error"], requireAll = false)
        fun loadImage(view: ImageView, profileImage: String, error: Int) {
            Glide.with(view.context)
                .load(profileImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.setImageResource(error)
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.setImageDrawable(resource)
                        return true
                    }
                })
                .into(view)
        }

    }

}