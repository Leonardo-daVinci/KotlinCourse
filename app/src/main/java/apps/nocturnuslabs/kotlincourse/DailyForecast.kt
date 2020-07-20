package apps.nocturnuslabs.kotlincourse

import java.util.*

data class DailyForecast(
    val date: Date,
    val temp : Float,
    val description : String
)