package com.rodrigotristany.mrjeff.ui.searchs

import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.internal.mvp.BaseMVP

interface RecentSearchesMVP {
    interface View : BaseMVP.View{
        fun showLoader()
        fun hideLoader()
        fun showCityList(cities: List<City>)
        fun sendDataToMap(city: City)
    }

    interface Presenter : BaseMVP.Presenter {
        fun setView(view: View)
    }
}