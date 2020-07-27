package apps.nocturnuslabs.kotlincourse

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//represents different types of locations in our app
sealed class Location{

    //this can be modified to take LAT_LONG coordinates of the place taken from GPS
    data class Zipcode(val zipcode: String) : Location()
}

//kotlin top-level constant
private const val KEY_ZIPCODE = "key_zipcode"

class LocationRepository(context: Context) {

    //we create preferences variable to save our location
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    //Mutable live data is handled by the repository while LiveData is used for exposing the data to other classes.
    //outside parties can observe changes in savedLocation which changes according to _savedLocation
    private val _savedLocation : MutableLiveData<Location> = MutableLiveData()
    val savedLocation : LiveData<Location> = _savedLocation

    init {//similar to constructor

        //savedPreferences change listener
        preferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            if(key!= KEY_ZIPCODE) return@registerOnSharedPreferenceChangeListener
            broadcastSavedZipcode()
            }

        //notify current value to observers
       broadcastSavedZipcode()

    }

    fun savedLocation (location: Location){
        when(location){
            //anytime we hit save location, its going to save it in Saved Preferences
            is Location.Zipcode -> preferences.edit().putString(KEY_ZIPCODE, location.zipcode).apply()
        }
    }

    private fun broadcastSavedZipcode(){
        val zipcode = preferences.getString(KEY_ZIPCODE, "")
        if (zipcode != null && zipcode.isNotBlank()) {
            _savedLocation.value = Location.Zipcode(zipcode)
        }
    }
}