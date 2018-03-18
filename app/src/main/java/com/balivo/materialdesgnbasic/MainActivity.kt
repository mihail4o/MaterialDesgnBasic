package com.balivo.materialdesgnbasic

import android.R.menu
import android.content.Intent
import android.support.v7.app.*
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private var toolbar:Toolbar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar=findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)

        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)

        val drawerFragment:NavigationDrawerFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer)
                as NavigationDrawerFragment

        drawerFragment.setUp(R.id.fragment_navigation_drawer, findViewById(R.id.drawer_layout)as DrawerLayout, toolbar as Toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {

            Toast.makeText(this, "Hey, you just hit!", Toast.LENGTH_LONG).show()
            return true
        }

        else if (id == R.id.navigate) {

            startActivity(Intent(this, SubActivity::class.java))
            return true

        } else return super.onOptionsItemSelected(item)
    }
}