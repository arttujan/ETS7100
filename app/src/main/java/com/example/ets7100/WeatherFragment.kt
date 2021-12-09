package com.example.ets7100

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.weather_fragment.*

class WeatherFragment : Fragment(){
    val TAG = "WeatherFragment"
    val city: String = "helsinki"
    val country: String = ",fi"
    val api: String = "8118ed6ee68db2debfaaa5a44c832918"

    override fun onAttach(context: Context) {
        Log.d(TAG,"onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        WeatherTask().execute()
        Log.d(TAG,"onCreateView")
        return inflater!!.inflate(R.layout.weather_fragment,container,false)
    }

    inner class WeatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */

        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$city$country&units=metric&appid=$api").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {

                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val tempValue = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val addressText = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                address.text = addressText
                updated_at.text = updatedAtText
                temp.text = tempValue
                temp_min.text = tempMin
                temp_max.text = tempMax

                /* Views populated, Hiding the loader, Showing the main design */


            } catch (e: Exception) {
                Log.d("virhe","VIRHE")
            }
        }
    }
}