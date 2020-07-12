package apps.nocturnuslabs.kotlincourse.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import apps.nocturnuslabs.kotlincourse.R
import apps.nocturnuslabs.kotlincourse.formatTemperature

class ForecastDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        setTitle(R.string.forecast_details)

        val tempText = findViewById<TextView>(R.id.forecast_details_temptext)
        val descriptionText = findViewById<TextView>(R.id.forecast_details_descriptiontext)

        val temp = intent.getFloatExtra("Temp", 0f)

        tempText.text = formatTemperature(temp)
        descriptionText.text = intent.getStringExtra("Description")
    }
}