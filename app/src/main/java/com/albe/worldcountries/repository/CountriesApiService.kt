package com.albe.worldcountries.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**  https://restcountries.com/v2/all?fields=name,capital,region,flag  */

enum class CountriesApiFilter(val value: String) {
    AFRICA("Africa"),
    AMERICAS("Americas"),
    ASIA("Asia"),
    EUROPE("Europe"),
    OCEANIA("Oceania")
}

/** Base URL for the countries api*/
private const val BASE_URL = "https://restcountries.com/v2/"

/** Moshi Object. Moshi converts JSON string into Kotlin objects*/
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/** The retrofit object used to fetch data from the Mars Api.*/
/** Moshi is used as converter*/
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**The interface that defines how retrofit talk to the web server using HTTP request*/
/**"all?fields=name,capital,region,flag" is the endpoint we will append to the API Base URL*/
 interface CountriesApiService {
     @GET("all?fields=name,capital,region,flags")
     suspend fun getCountries() : List<Country>
 }

/**Public Object that inits the Retrofit service and exposes the service to the rest of the app*/
object CountriesApi {
    //gets a retrofit Singleton Object that implements the CountriesApiService
    val retrofitService : CountriesApiService by lazy{
        retrofit.create(CountriesApiService::class.java)
    }
}

