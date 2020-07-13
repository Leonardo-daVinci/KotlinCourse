package apps.nocturnuslabs.kotlincourse.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import apps.nocturnuslabs.kotlincourse.*

class ForecastDetailsActivity : AppCompatActivity() {

    private lateinit var displaySettingManager : DisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        //change the title of the appbar
        setTitle(R.string.forecast_details)

        val tempText = findViewById<TextView>(R.id.forecast_details_temptext)
        val descriptionText = findViewById<TextView>(R.id.forecast_details_descriptiontext)

        //getting values from the intent we used in MainActivity
        val temp = intent.getFloatExtra("Temp", 0f)

        displaySettingManager = DisplaySettingManager(this)
        tempText.text = formatTemperature(temp, displaySettingManager.getDisplaySetting())
        descriptionText.text = intent.getStringExtra("Description")
    }

    //this is used to show the menu which we have created
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    //this is to handle click events on the menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_temp_display -> {
                showTempDisplaySetting()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //creating an alert dialog
    private fun showTempDisplaySetting(){
        AlertDialog.Builder(this)
            .setTitle("Choose Display Units")
            .setMessage("Choose which temperature unit to use.")
            .setPositiveButton("°F"){ _ , _ ->
                displaySettingManager.updateSetting(DisplaySetting.Fahrenheit)
            }
            .setNeutralButton("°C") { _, _ ->
                displaySettingManager.updateSetting(DisplaySetting.Celsius)
            }
            .setOnDismissListener{
                Toast.makeText(this, "Settings will take affect after app restart",Toast.LENGTH_SHORT).show()
            }
            .show()
    }

}