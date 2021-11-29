package com.albe.worldcountries.ui.listofcountries

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.albe.worldcountries.R
import com.albe.worldcountries.databinding.ListOfCountriesFragmentBinding

class ListOfCountriesFragment : Fragment() {

    private val viewModel: ListOfCountriesViewModel by lazy {
        ViewModelProvider(this).get(ListOfCountriesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = ListOfCountriesFragmentBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.countriesGrid.adapter = CountriesGridAdapter(CountriesGridAdapter.OnClickListener{
            viewModel.displayCountryDetails(it)
        })

        viewModel.navigateToSelectedCountry.observe(this, Observer {
            if (null != it){
                this.findNavController().navigate(
                    ListOfCountriesFragmentDirections.actionShowCountryDetail(it))
                viewModel.displayCountryDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        viewModel.updateFilter(
//            when (item.itemId) {
//                R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
//                R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
//                else -> MarsApiFilter.SHOW_ALL
//            }
//        )
        return true
    }

}