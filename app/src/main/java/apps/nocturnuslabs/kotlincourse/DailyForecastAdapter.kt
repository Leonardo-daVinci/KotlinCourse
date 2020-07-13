package apps.nocturnuslabs.kotlincourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DailyForecastViewHolder(view: View,
    private val displaySettingManager: DisplaySettingManager
) : RecyclerView.ViewHolder(view){

    private val tempText = view.findViewById<TextView>(R.id.temptext)
    private val descriptionText = view.findViewById<TextView>(R.id.descriptiontext)

    fun bind(dailyForecast: DailyForecast) {
        tempText.text = formatTemperature(dailyForecast.temp, displaySettingManager.getDisplaySetting())
        descriptionText.text = dailyForecast.description
    }
}

class DailyForecastAdapter(
    //this is used to implement clicking on individual list items
    private val displaySettingManager: DisplaySettingManager,
    private val clickHandler : (DailyForecast)-> Unit
) : ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {

    //anything written here would exist outside the Adapter class
    //it is similar to static in Java
    companion object{

        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>(){
            //object refers to instance of an anonymous class
            //itemcallback will work on DailyForecast items

            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
               // === operator is used to check if they are same object references
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        //this creates a new view holder within which we create a new view which would represent each item in our list
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(itemView, displaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        //get forecast items and pass that to view holder so that view items can be updated.
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }
}