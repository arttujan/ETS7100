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
    /*Paikkakuntatiedot ja API-key*/
    val TAG = "WeatherFragment"
    val city: String = "kuopio"
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

        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$api").readText(
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

                /* JSON tulee APILTA*/
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Päivitetty: "+ SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).format(Date(updatedAt*1000))
                val tempValue = main.getString("temp")+"°C"
                val tempMin = "Alin: " + main.getString("temp_min")+"°C"
                val tempMax = "Ylin: " + main.getString("temp_max")+"°C"
                val addressText = jsonObj.getString("name")

                /* Laitetaan säädata kenttiin */
                address.text = addressText
                updated_at.text = updatedAtText
                temp.text = tempValue
                temp_min.text = tempMin
                temp_max.text = tempMax

            } catch (e: Exception) {
                Log.d("virhe","VIRHE")
            }
        }
    }
}