package com.albe.worldcountries.ui.countrydetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albe.worldcountries.repository.Country

class CountryDetailViewModelFactory(
    private val country : Country,
    private val application: Application ): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryDetailViewModel::class.java)) {
            return CountryDetailViewModel(country, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}