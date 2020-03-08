package com.rodrigotristany.mrjeff.ui.searchs

import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.internal.mvp.BaseMVP

interface RecentSearchMVP {
    interface View : BaseMVP.View{
        fun showCityList(cities: List<City>)
    }

    interface Presenter : BaseMVP.Presenter {
    }
}