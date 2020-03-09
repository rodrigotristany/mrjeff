package com.rodrigotristany.mrjeff.ui.maps.di

import com.rodrigotristany.mrjeff.internal.di.components.ApplicationComponent
import com.rodrigotristany.mrjeff.internal.di.scope.PerActivity
import com.rodrigotristany.mrjeff.ui.maps.MapsActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [MapsModule::class])
interface MapsComponent {
    fun inject(mapsActivity: MapsActivity)
}