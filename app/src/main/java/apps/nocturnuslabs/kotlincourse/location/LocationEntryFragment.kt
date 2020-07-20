package apps.nocturnuslabs.kotlincourse.location

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import apps.nocturnuslabs.kotlincourse.Location
import apps.nocturnuslabs.kotlincourse.LocationRepository
import apps.nocturnuslabs.kotlincourse.R

class LocationEntryFragment : Fragment() {

    private lateinit var locationRepository : LocationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        locationRepository = LocationRepository(requireContext())

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_location_entry, container, false)

        val zipcodeEditText : EditText = view.findViewById(R.id.main_editText)
        val enterButton : Button = view.findViewById(R.id.main_forecast_btn)

        enterButton.setOnClickListener {
            val zipcode : String = zipcodeEditText.text.toString()

            locationRepository.savedLocation(Location.Zipcode(zipcode))
            findNavController().navigateUp()
        }

        return view
    }
}