package com.rodrigotristany.mrjeff.ui.searchs

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.internal.extensions.inflate
import kotlinx.android.synthetic.main.searchs_list_item.view.*

data class SearchsListAdapter(val cities: List<City>, val listener: (City) -> Unit)
    : RecyclerView.Adapter<SearchsListAdapter.Companion.ListItemViewHolder>() {

    companion object {
        class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var cityName: TextView = itemView.text_view_city_name
            var locationPoint: TextView = itemView.text_view_location_point

            fun bind(city: City){
                cityName.text = city.name
                locationPoint.text = "Lat: ${city.lat} - Lng: ${city.lng}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        var view = parent.inflate(R.layout.searchs_list_item)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(cities[position])
        holder.itemView.setOnClickListener { listener(cities[position]) }
    }

    override fun getItemCount() = cities.size
}