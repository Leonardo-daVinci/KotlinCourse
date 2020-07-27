package apps.nocturnuslabs.kotlincourse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import apps.nocturnuslabs.kotlincourse.api.CurrentWeather
import apps.nocturnuslabs.kotlincourse.api.createOpenWeatherMapService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.random.Random

class ForecastRepo {

    //region Current Forecast
    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> = _currentWeather

    fun loadCurrentForecast(zipcode: String) {
        val call = createOpenWeatherMapService().currentWeather(
            zipcode,
            "imperial",
            BuildConfig.OPEN_WEATHER_MAP_API_KEY
        )
        call.enqueue(object : Callback<CurrentWeather> {
            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                Log.e(ForecastRepo::class.java.simpleName, "Error loading weather", t)
            }

            override fun onResponse(
                call: Call<CurrentWeather>,
                response: Response<CurrentWeather>
            ) {
                val weatherResponse = response.body()
                if (weatherResponse != null) {
                    _currentWeather.value = weatherResponse
                }
            }

        })
    }
    //endregion

    //region Weekly Forecast
    //internal to our forecast used to update data
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()

    //data that the ui would listen to for the changes
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    //load the data into weekly forecast
    fun loadWeeklyForecast(zipcode: String){

        //generates List of Size seven and then initializes it using random function
        //rem works like Modulus Function
        val randomValues = List(8){ Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map{
            DailyForecast(Date(), it, getTempDescription(it))
        }
        _weeklyForecast.value = forecastItems
    }
    //endregion

    private fun getTempDescription(temp: Float ): String = when(temp){
        in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense"
        in 0f.rangeTo(32f) -> "Way too cold"
        in 32f.rangeTo(55f) -> "Colder than I would prefer"
        in 55f.rangeTo(65f) -> "Getting better"
        in 65f.rangeTo(80f) -> "That's the sweet spot!"
        in 80f.rangeTo(90f) -> "Getting a little warm"
        in 90f.rangeTo(100f) -> "Where's the A/C?"
        in 100f.rangeTo(Float.MAX_VALUE) -> "What is this, Arizona?"
        else -> "Does not compute"
    }

}