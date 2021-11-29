package com.albe.worldcountries.ui.countrydetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albe.worldcountries.repository.Country

class CountryDetailViewModel(country: Country, app: Application) : AndroidViewModel(app) {
     private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country>
    get() = _selectedCountry

    init {
        _selectedCountry.value = country
    }
}