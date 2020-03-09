package com.rodrigotristany.mrjeff.domain.cities

import com.rodrigotristany.mrjeff.data.cities.models.Bbox
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.data.cities.models.CityResponse
import com.rodrigotristany.mrjeff.data.cities.models.Timezone
import com.rodrigotristany.mrjeff.data.cities.repository.CityRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(CityRepository::class)
class GetCitiesUseCaseTest {
    private lateinit var getCitiesUseCase: GetCitiesUseCase

    @Mock
    lateinit var cityRepository: CityRepository

    val cities = listOf(
        City(Bbox(10, -3.5, 40.0, 40.0, -3.8), "España", 40.41, -3.702, "Hola_Madrid", 200000, 1, Timezone(2f, 1f, "Europe/Madrid")),
        City(Bbox(10, -3.5, 40.0, 40.0, -3.8), "España", 40.41, -3.702, "Hola_Barcelona", 200000, 1, Timezone(2f, 1f, "Europe/Madrid")),
        City(Bbox(10, -3.5, 40.0, 40.0, -3.8), "España", 40.41, -3.702, "Hola_Valencia", 200000, 1, Timezone(2f, 1f, "Europe/Madrid"))
    )

    val cityResponse = CityResponse(cities, null)

    @Before
    fun setUp() {
        `when`(cityRepository.cities("hola"))
            .thenReturn(Observable.just(cityResponse))

        `when`(cityRepository.cities("Madrid"))
            .thenReturn(Observable.just(cityResponse))


        getCitiesUseCase = GetCitiesUseCase(cityRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @After
    fun tearDown() {
        getCitiesUseCase.dispose()
    }

    @Test
    fun `should return 3 matching cities`() {
        val observer = TestObserver<CityResponse>()
        getCitiesUseCase.execute(observer, "hola")
        observer.assertNoErrors()
        observer.assertComplete()
        assertEquals(true, observer.values()[0].statusOk())
        assertEquals(3, observer.values()[0].cities.size)
    }
}