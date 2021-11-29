package com.albe.worldcountries.ui.listofcountries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.albe.worldcountries.databinding.GridViewItemBinding
import com.albe.worldcountries.repository.Country

class CountriesGridAdapter (private val onClickListener: OnClickListener) :
    ListAdapter<Country,
            CountriesGridAdapter.CountryViewHolder>(DiffCallback){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : CountryViewHolder {
        return CountryViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int){
        val country = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(country)
        }
        holder.bind(country)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Country>(){
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }
    }



    class CountryViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(country : Country){
            binding.country = country
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (country : Country)-> Unit){
        fun onClick(country: Country) = clickListener(country)
    }
}