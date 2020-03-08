package com.rodrigotristany.mrjeff.domain.cities

import com.rodrigotristany.mrjeff.data.cities.dao.CityDao
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.domain.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetHistorySearchUseCase
@Inject constructor(private val cityDao: CityDao,
                    subscribeScheduler: Scheduler,
                    postExecutionScheduler: Scheduler) : UseCase<List<City>, Unit>(subscribeScheduler, postExecutionScheduler) {
    override fun buildUseCaseSingle(params: Unit?): Observable<List<City>> = cityDao.recentSearch()
}