package com.rodrigotristany.mrjeff.ui.searchs.di

import com.rodrigotristany.mrjeff.internal.di.components.ApplicationComponent
import com.rodrigotristany.mrjeff.internal.di.scope.PerActivity
import com.rodrigotristany.mrjeff.ui.searchs.RecentSearchesActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [RecentSearchsModule::class])
interface RecentSearchesComponent {
    fun inject(recentSearchesActivity: RecentSearchesActivity)
}