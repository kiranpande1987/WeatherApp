package com.kprights.weatherapp

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.kprights.weatherapp.view.FavouriteCityListAdapter


class MainActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        (drawerLayout as DrawerLayout).addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val adapter = FavouriteCityListAdapter(FavouriteCityListAdapter.OnClickListener {

        })

        val listView = findViewById<RecyclerView>(R.id.listOfFavCities)

        listView.adapter = adapter

        adapter.submitList(mutableListOf("1", "2", "3", "4"))
    }

    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}