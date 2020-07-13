package apps.nocturnuslabs.kotlincourse

import android.content.Context

//this is used to specify what settings are available in our app.
enum class DisplaySetting {
    Fahrenheit, Celsius
}

class DisplaySettingManager(context: Context){
    //shared preferences help create a file on the disk which holds key-value pairs that would persist when application restarts
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSetting(setting : DisplaySetting){
        preferences.edit().putString("key_temp_display", setting.name).apply()
    }

    fun getDisplaySetting(): DisplaySetting {
        val settingValue = preferences.getString("key_temp_display", DisplaySetting.Fahrenheit.name) ?: DisplaySetting.Fahrenheit.name
        return DisplaySetting.valueOf(settingValue)
    }
}