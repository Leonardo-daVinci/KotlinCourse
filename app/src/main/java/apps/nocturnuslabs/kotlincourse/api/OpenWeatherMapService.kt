package apps.nocturnuslabs.kotlincourse.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//this creates a retrofit object and that would help us create the service
fun createOpenWeatherMapService(): OpenWeatherMapService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(OpenWeatherMapService::class.java)
}

interface OpenWeatherMapService {

    @GET("/data/2.5/weather")

    fun currentWeather(
        //this should be similar to the API's query
        @Query("zip") zipcode: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<CurrentWeather>

}