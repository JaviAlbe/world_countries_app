package com.albe.worldcountries.ui.listofcountries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albe.worldcountries.repository.CountriesApi
import com.albe.worldcountries.repository.Country
import kotlinx.coroutines.launch
import java.lang.Exception

class ListOfCountriesViewModel : ViewModel() {

    //The status of the api call
    enum class CountriesApiStatus { LOADING, ERROR, DONE }

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()
    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    //The list of countries
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    //The api request status
    private val _status = MutableLiveData<CountriesApiStatus>()
    val status: LiveData<CountriesApiStatus>
        get() = _status

    private val _navigateToSelectedCountry = MutableLiveData<Country>()
    val navigateToSelectedCountry: LiveData<Country>
        get() = _navigateToSelectedCountry


    init {
        getCountries()
    }

    private fun getCountries(){
        viewModelScope.launch {
            _status.value = CountriesApiStatus.LOADING
            try {
                _countries.value = CountriesApi.retrofitService.getCountries()
                _status.value = CountriesApiStatus.DONE
            } catch (e: Exception){
                //If we get an error we clear the list of countries
                _status.value = CountriesApiStatus.ERROR
                _countries.value = ArrayList()
            }
        }
    }

    fun displayCountryDetails(country: Country) {
        _navigateToSelectedCountry.value = country
    }

    //nulls the value of _navigateToSelectedCountry and is called when we navigated to country detail
    fun displayCountryDetailsComplete() {
        _navigateToSelectedCountry.value = null
    }
}