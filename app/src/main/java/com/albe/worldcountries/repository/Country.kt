package com.albe.worldcountries.repository

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    @Json(name = "flags") val flags: Flag,
    val name: String,
    val capital: String,
    val region: String

) : Parcelable {

    fun getPNGFlag() : String {
        return flags.flagPNGFormat
    }
}

