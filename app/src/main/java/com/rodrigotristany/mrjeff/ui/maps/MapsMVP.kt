package com.rodrigotristany.mrjeff.ui.maps

import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.internal.mvp.BaseMVP

interface MapsMVP {
    interface View : BaseMVP.View {
        fun setLocationPoint(lat: Double, lng: Double)
        fun displayCityInfo(temperature: Double)
    }

    interface Presenter {
        fun searchCityWeather(city: City)
        fun setView(view: View)
    }
}