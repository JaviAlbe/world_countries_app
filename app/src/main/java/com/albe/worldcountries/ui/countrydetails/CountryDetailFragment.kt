package com.albe.worldcountries.ui.countrydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.albe.worldcountries.databinding.CountryDetailFragmentBinding

class CountryDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = CountryDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val country = CountryDetailFragmentArgs.fromBundle(arguments!!).selectedCountry

        val viewModelFactory = CountryDetailViewModelFactory(country, application)

        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(CountryDetailViewModel::class.java)

        return binding.root
    }
}