package apps.nocturnuslabs.kotlincourse.details

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import apps.nocturnuslabs.kotlincourse.*

class ForecastDetailsFragment : Fragment() {

    private  val args : ForecastDetailsFragmentArgs by navArgs()
    private lateinit var displaySettingManager : DisplaySettingManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_forecast_details, container, false)

        displaySettingManager = DisplaySettingManager(requireContext())

        val tempText = layout.findViewById<TextView>(R.id.forecast_details_temptext)
        val descriptionText = layout.findViewById<TextView>(R.id.forecast_details_descriptiontext)

        //this is used to get the passed arguments using the safe-args functionality
        tempText.text = formatTemperature(args.temp, displaySettingManager.getDisplaySetting())
        descriptionText.text = args.description

        return layout
    }

}