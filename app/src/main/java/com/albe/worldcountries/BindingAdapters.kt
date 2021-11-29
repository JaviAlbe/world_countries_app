package com.albe.worldcountries

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.albe.worldcountries.repository.Country
import com.albe.worldcountries.ui.listofcountries.CountriesGridAdapter
import com.albe.worldcountries.ui.listofcountries.ListOfCountriesViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Country>?){
    val adapter = recyclerView.adapter as CountriesGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("countriesApiStatus")
fun bindStatus(statusImageView: ImageView, status: ListOfCountriesViewModel.CountriesApiStatus?) {
    when (status) {
        ListOfCountriesViewModel.CountriesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ListOfCountriesViewModel.CountriesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        ListOfCountriesViewModel.CountriesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}