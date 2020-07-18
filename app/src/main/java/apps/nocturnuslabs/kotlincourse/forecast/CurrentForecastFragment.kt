package apps.nocturnuslabs.kotlincourse.forecast

import android.content.Context
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
import apps.nocturnuslabs.kotlincourse.details.ForecastDetailsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CurrentForecastFragment : Fragment() {

    private lateinit var appNavigator: AppNavigator

    //this function is called when fragment lifecycle starts
    override fun onAttach(context: Context) {
        super.onAttach(context)

        //assign value of context to appNavigator which will then have reference to our MainActivity
        appNavigator = context as AppNavigator
    }

    private lateinit var displaySettingManager: DisplaySettingManager
    private val forecastRepository = ForecastRepo()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        displaySettingManager = DisplaySettingManager(requireContext())

        val zipcode = requireArguments().getString(KEY_ZIPCODE) ?: ""

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)

        // region RecyclerView Implementation
        val forecastList : RecyclerView = view.findViewById(R.id.main_forecast_list)

        //we'll need a layout manager to know how the items should be laid out on the screen
        forecastList.layoutManager = LinearLayoutManager(requireContext())

        //Steps for Adapter  - view holder, adapter and item callback
        val dailyForecastAdapter = DailyForecastAdapter(displaySettingManager){
            appNavigator.navigateToForecastDetails(it)
        }
        forecastList.adapter = dailyForecastAdapter

        //endregion RecyclerView

        val locationEntryButton : FloatingActionButton = view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener {
            appNavigator.navigateToLocationEntry()
        }

        //adding observer for the repository which updates anytime Livedata is updated in the repo
        val weeklyForecastObserver = Observer<List<DailyForecast>>{ forecastItems ->
            //update our list adapter
            Toast.makeText(requireContext(), "Loaded Items", Toast.LENGTH_SHORT).show()
            dailyForecastAdapter.submitList(forecastItems)
        }

        forecastRepository.weeklyForecast.observe(viewLifecycleOwner, weeklyForecastObserver) //observe function requires a lifecycle owner and an observer

        forecastRepository.loadForecast(zipcode)
        return view
    }

    //to get Zipcode parameter to our CurrentForecastFragment
    companion object{
        //this will be used to get the key-value pair for zipcode
        const val KEY_ZIPCODE = "key_zipcode"

        //newInstance function is used to add new fragments (ITS A CONVENTION)
        //it acts like a factory method for the fragment and takes parameters that fragment need to operate correctly
        fun newInstance(zipcode: String) : CurrentForecastFragment{
            val fragment = CurrentForecastFragment()

            //bundle is a class that is designed to store key-value pairs
            val args = Bundle()
            args.putString(KEY_ZIPCODE, zipcode)
            fragment.arguments = args

            return fragment
        }

    }

}