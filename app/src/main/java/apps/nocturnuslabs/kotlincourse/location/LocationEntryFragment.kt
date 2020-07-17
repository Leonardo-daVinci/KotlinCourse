package apps.nocturnuslabs.kotlincourse.location

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import apps.nocturnuslabs.kotlincourse.AppNavigator
import apps.nocturnuslabs.kotlincourse.R

class LocationEntryFragment : Fragment() {

    private lateinit var appNavigator: AppNavigator

    //this function is called when fragment lifecycle starts
    override fun onAttach(context: Context) {
        super.onAttach(context)

        //assign value of context to appNavigator which will then have reference to our MainActivity
        appNavigator = context as AppNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_location_entry, container, false)

        val zipcodeEditText : EditText = view.findViewById(R.id.main_editText)
        val enterButton : Button = view.findViewById(R.id.main_forecast_btn)

        enterButton.setOnClickListener {
            val zipcode : String = zipcodeEditText.text.toString()

            appNavigator.navigateToCurrentForecast(zipcode)
        }

        return view
    }
}