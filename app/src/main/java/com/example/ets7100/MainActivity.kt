package com.example.ets7100
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), Communicator {

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showWeatherFragment()
        showSearchFragment()
        passDataCom(editTextInput = String.toString())
    }

    private fun showWeatherFragment() {
        val transaction = manager.beginTransaction()
        val fragment1 = WeatherFragment()
        transaction.replace(R.id.weather_fragment_holder, fragment1)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showSearchFragment() {
        val transaction = manager.beginTransaction()
        val fragment2 = SearchFragment()
        transaction.replace(R.id.search_fragment_holder, fragment2)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun passDataCom(editTextInput: String) {
        val transaction = manager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("paikkakunta", editTextInput)
        val fragment = WeatherFragment()
        fragment.arguments = bundle
        transaction.replace(R.id.weather_fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}
