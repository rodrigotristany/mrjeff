package com.rodrigotristany.mrjeff.internal.di.modules

import android.content.Context
import androidx.room.Room
import com.rodrigotristany.mrjeff.data.api.GeoNamesApiService
import com.rodrigotristany.mrjeff.data.cities.dao.CityDao
import com.rodrigotristany.mrjeff.data.cities.repository.CityRepository
import com.rodrigotristany.mrjeff.data.db.AppDatabase
import com.rodrigotristany.mrjeff.data.weather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DataModule{
    val BASE_URL: String = "http://api.geonames.org/"

    @Provides
    @Singleton
    fun provideDatabaseClient(context: Context) : AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "mr-jeff-database"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideClient() : OkHttpClient {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                var request : Request = chain.request()
                val url : HttpUrl = request .url().newBuilder().build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGeonamesApi() : GeoNamesApiService {
        return provideRetrofit(BASE_URL, provideClient()).create(GeoNamesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCityRepository() : CityRepository {
        return CityRepository(provideGeonamesApi())
    }

    @Provides
    @Singleton
    fun provideWeatherRepository() : WeatherRepository {
        return WeatherRepository(provideGeonamesApi())
    }

    @Provides
    @Singleton
    fun provideCityDao(context: Context) : CityDao {
        val db = provideDatabaseClient(context)
        return db.cityDao()
    }
}