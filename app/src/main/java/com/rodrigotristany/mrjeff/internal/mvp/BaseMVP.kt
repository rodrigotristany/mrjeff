package com.rodrigotristany.mrjeff.internal.mvp

interface BaseMVP {
    interface View {
        fun showLoader()
        fun hideLoader()
        fun showToast(message: String?)
    }
}