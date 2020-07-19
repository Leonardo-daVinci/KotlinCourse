package apps.nocturnuslabs.kotlincourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import apps.nocturnuslabs.kotlincourse.forecast.CurrentForecastFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var displaySettingManager : DisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displaySettingManager = DisplaySettingManager(this)

        //this connects the NavController to our toolbar
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController,appBarConfiguration)

        //this connects the bottomNavigationView to our navController
        findViewById<BottomNavigationView>(R.id.bottom_nav_view).setupWithNavController(navController)
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
                Toast.makeText(this, "Settings will take affect after app restart", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}