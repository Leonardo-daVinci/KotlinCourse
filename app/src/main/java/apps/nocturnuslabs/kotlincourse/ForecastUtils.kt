package apps.nocturnuslabs.kotlincourse

fun formatTemperature(temp: Float, displaySetting: DisplaySetting): String = when(displaySetting){
    DisplaySetting.Fahrenheit -> String.format("%.2f °F", temp)
    DisplaySetting.Celsius -> {
        val temp = (temp - 32f)*(5f/9f)
        String.format("%.2f °C", temp)
    }
}
