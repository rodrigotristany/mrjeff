package com.rodrigotristany.mrjeff.internal.mvp

interface BaseMVP {
    interface View {
        fun showSnackBar(message: String?)
        fun showLoader()
        fun hideLoader()
    }

    interface Presenter {
        fun initialize()
    }
}