package apps.nocturnuslabs.kotlincourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.nocturnuslabs.kotlincourse.details.ForecastDetailsActivity
import apps.nocturnuslabs.kotlincourse.forecast.CurrentForecastFragment
import apps.nocturnuslabs.kotlincourse.location.LocationEntryFragment

class MainActivity : AppCompatActivity(), AppNavigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //showing the fragment in activity_main layout
        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment_container,  LocationEntryFragment())
            .commit()
    }

    override fun navigateToCurrentForecast(zipcode: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, CurrentForecastFragment())
            .commit()
    }
}