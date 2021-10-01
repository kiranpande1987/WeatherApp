package com.kprights.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kprights.weatherapp.view.CityWeatherFragment
import com.kprights.weatherapp.view.FavouriteCityListAdapter


class MainActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var adapter: FavouriteCityListAdapter? = null
    private lateinit var listViewFavCities: RecyclerView
    private lateinit var txtNoFavCities: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        (drawerLayout as DrawerLayout).addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adapter = FavouriteCityListAdapter(FavouriteCityListAdapter.OnClickListener {
            val fragment = supportFragmentManager.fragments[0] as CityWeatherFragment
            fragment.loadWeatherForCityName(it)

            drawerLayout?.closeDrawers()
        })

        listViewFavCities = findViewById(R.id.listOfFavCities)
        txtNoFavCities = findViewById(R.id.txtNoFavCities)
        listViewFavCities.adapter = adapter
    }

    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            val sharedPref = getPreferences(Context.MODE_PRIVATE)
            val currentFavCities = sharedPref?.getString("FavouriteCities", null)
            val cities = currentFavCities?.split(":")

            if(cities.isNullOrEmpty()){
                listViewFavCities.visibility = View.GONE
                txtNoFavCities.visibility = View.VISIBLE
            }
            else{
                listViewFavCities.visibility = View.VISIBLE
                txtNoFavCities.visibility = View.GONE
                adapter?.submitList(cities)
            }

            true
        } else super.onOptionsItemSelected(item)
    }
}