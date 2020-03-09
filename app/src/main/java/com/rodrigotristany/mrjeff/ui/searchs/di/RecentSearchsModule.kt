package com.rodrigotristany.mrjeff.ui.searchs.di

import android.content.Context
import com.rodrigotristany.mrjeff.domain.cities.GetCitiesUseCase
import com.rodrigotristany.mrjeff.domain.cities.GetHistorySearchUseCase
import com.rodrigotristany.mrjeff.internal.di.scope.PerActivity
import com.rodrigotristany.mrjeff.ui.searchs.RecentSearchesMVP
import com.rodrigotristany.mrjeff.ui.searchs.RecentSearchesPresenter
import dagger.Module
import dagger.Provides

@Module
class RecentSearchsModule {
    @PerActivity
    @Provides
    fun provideRecentsSearchsPresenter(getCitiesUseCase: GetCitiesUseCase,
                                       getHistorySearchUseCase: GetHistorySearchUseCase,
                                       context: Context) : RecentSearchesMVP.Presenter {
        return RecentSearchesPresenter(getCitiesUseCase, getHistorySearchUseCase, context)
    }
}