package com.albe.worldcountries.repository

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flag(
    @Json(name = "svg") val flagSVGFormat: String,
    @Json(name = "png") val flagPNGFormat: String
) : Parcelable
