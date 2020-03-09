package com.rodrigotristany.mrjeff.domain.cities

import com.rodrigotristany.mrjeff.data.cities.dao.CityDao
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.domain.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class SaveLastSearchUseCase
@Inject constructor(private val cityDao: CityDao,
                    subscribeScheduler: Scheduler,
                    postExecutionScheduler: Scheduler) : UseCase<Long, City>(subscribeScheduler, postExecutionScheduler) {
    override fun buildUseCaseSingle(params: City?): Observable<Long> = cityDao.saveSearch(params!!).toObservable()
}