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

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepo()
    private lateinit var displaySettingManager: DisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displaySettingManager = DisplaySettingManager(this)

        val zipcodeEditText : EditText = findViewById(R.id.main_editText)
        val enterButton : Button = findViewById(R.id.main_forecast_btn)

        enterButton.setOnClickListener {
            val zipcode : String = zipcodeEditText.text.toString()
            forecastRepository.loadForecast(zipcode)
        }

        // region RecyclerView Implementation
        val forecastList : RecyclerView = findViewById(R.id.main_forecast_list)

        //we'll need a layout manager to know how the items should be laid out on the screen
        forecastList.layoutManager = LinearLayoutManager(this)

        //Steps for Adapter  - view holder, adapter and item callback
        val dailyForecastAdapter = DailyForecastAdapter(displaySettingManager){
            val forecastDetailsIntent = Intent(this, ForecastDetailsActivity::class.java)
            forecastDetailsIntent.putExtra("Temp", it.temp)
            forecastDetailsIntent.putExtra("Description", it.description)
            startActivity(forecastDetailsIntent)
        }
        forecastList.adapter = dailyForecastAdapter

        //endregion RecyclerView


        //adding observer for the repository which updates anytime Livedata is updated in the repo
        val weeklyForecastObserver = Observer<List<DailyForecast>>{forecastItems ->
            //update our list adapter
            Toast.makeText(this, "Loaded Items",Toast.LENGTH_SHORT).show()
            dailyForecastAdapter.submitList(forecastItems)
        }

        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver) //observe function requires a lifecycle owner and an observer
    }
}