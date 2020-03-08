package com.rodrigotristany.mrjeff.ui.maps

interface MapsMVP {
    interface View {
        fun showLoader()
        fun hideLoader()
    }

    interface Presenter {
        fun searchCity(value: String)
    }
}