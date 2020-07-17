package apps.nocturnuslabs.kotlincourse.forecast

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.nocturnuslabs.kotlincourse.*
import apps.nocturnuslabs.kotlincourse.details.ForecastDetailsActivity

class CurrentForecastFragment : Fragment() {

    private lateinit var displaySettingManager: DisplaySettingManager
    private val forecastRepository = ForecastRepo()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        displaySettingManager = DisplaySettingManager(requireContext())

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)

        // region RecyclerView Implementation
        val forecastList : RecyclerView = view.findViewById(R.id.main_forecast_list)

        //we'll need a layout manager to know how the items should be laid out on the screen
        forecastList.layoutManager = LinearLayoutManager(requireContext())

        //Steps for Adapter  - view holder, adapter and item callback
        val dailyForecastAdapter = DailyForecastAdapter(displaySettingManager){
            val forecastDetailsIntent = Intent(requireContext(), ForecastDetailsActivity::class.java)
            forecastDetailsIntent.putExtra("Temp", it.temp)
            forecastDetailsIntent.putExtra("Description", it.description)
            startActivity(forecastDetailsIntent)
        }
        forecastList.adapter = dailyForecastAdapter

        //endregion RecyclerView


        //adding observer for the repository which updates anytime Livedata is updated in the repo
        val weeklyForecastObserver = Observer<List<DailyForecast>>{ forecastItems ->
            //update our list adapter
            Toast.makeText(requireContext(), "Loaded Items", Toast.LENGTH_SHORT).show()
            dailyForecastAdapter.submitList(forecastItems)
        }

        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver) //observe function requires a lifecycle owner and an observer


        return view
    }

    companion object{

    }

}