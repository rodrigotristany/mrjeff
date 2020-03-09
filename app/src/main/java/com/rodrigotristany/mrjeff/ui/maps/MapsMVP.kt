package com.rodrigotristany.mrjeff.ui.maps

import com.rodrigotristany.mrjeff.internal.mvp.BaseMVP

interface MapsMVP {
    interface View : BaseMVP.View {
        fun setLocationPoint(lat: Double, lng: Double)
    }

    interface Presenter : BaseMVP.Presenter {
        fun searchCity(value: String)
    }
}