package com.example.ets7100
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.search_fragment.*

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showWeatherFragment()
        showSearchFragment()
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


}
