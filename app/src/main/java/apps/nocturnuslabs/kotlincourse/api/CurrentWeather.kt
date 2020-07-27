package apps.nocturnuslabs.kotlincourse.api

import com.squareup.moshi.Json

//we will define the models needs to define the response from the weather API

data class Forecast(val temp: Float)
data class Coordinates(val lat: Float, val lon: Float)

data class CurrentWeather(
    val name: String, //saves location that was passed
    val coordinates: Coordinates,

    //to have the variables names more semantically correct, we can use the following annotation
    //this tells retrofit how to parse the result meanwhile we can call it whatever we want

    //like the response has property "main" which has the temperature so we use it.
    @field: Json(name = "main") val forecast: Forecast
)