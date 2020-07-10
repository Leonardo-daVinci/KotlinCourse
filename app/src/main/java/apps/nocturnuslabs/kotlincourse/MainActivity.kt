package apps.nocturnuslabs.kotlincourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText : EditText = findViewById(R.id.main_editText)
        val enterButton : Button = findViewById(R.id.main_forecast_btn)

        enterButton.setOnClickListener {
            val zipcode : String = zipcodeEditText.text.toString()
        }

        //adding observer for the repository which updates anytime Livedata is updated in the repo
        val weeklyForecastObserver = Observer<List<DailyForecast>>{forecastItems ->
            //update our list adapter
        }

        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver) //observe function requires a lifecycle owner and an observer
    }
}