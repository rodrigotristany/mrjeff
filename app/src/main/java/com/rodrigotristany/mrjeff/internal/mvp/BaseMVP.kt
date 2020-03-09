package com.rodrigotristany.mrjeff.internal.mvp

interface BaseMVP {
    interface View {
        fun showToast(message: String?)
    }

    interface Presenter {
        fun initialize()
    }
}