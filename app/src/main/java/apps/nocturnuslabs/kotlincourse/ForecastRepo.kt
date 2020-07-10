package apps.nocturnuslabs.kotlincourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepo {


    //internal to our forecast used to update data
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()

    //data that the ui would listen to for the changes
    val weeklyForecast : LiveData<List<DailyForecast>> = _weeklyForecast

    //load the data into weeklyforecast
    fun loadForecast(zipcode: String){

        //generates List of Size seven and then initializes it using random function
        //rem works like Modulus Function
        val randomValues = List(7){ Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map{
            DailyForecast(it, "Partly Cloudy")
        }
        _weeklyForecast.value = forecastItems
    }


}