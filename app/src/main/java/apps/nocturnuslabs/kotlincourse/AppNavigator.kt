package apps.nocturnuslabs.kotlincourse

interface AppNavigator {
    //this function will be used to navigate to the required forecast page when we enter zipcode
    fun navigateToCurrentForecast(zipcode: String)
    fun navigateToLocationEntry()
}