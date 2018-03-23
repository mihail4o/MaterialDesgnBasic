package com.balivo.materialdesgnbasic

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent

class SubActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val toolbar = findViewById(R.id.app_bar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setHomeButtonEnabled(true)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu:Menu):Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu)
        return true
    }
    override fun onOptionsItemSelected(item:MenuItem):Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()
        if (id == R.id.action_settings)
        {
            return true
        }
        if (id == android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun dispatchTouchEvent(ev:MotionEvent):Boolean {
        when (ev.getActionMasked()) {
            MotionEvent.ACTION_DOWN -> Log.d(TAG, "Activity dispatchTouchEvent DOWN")
            MotionEvent.ACTION_MOVE -> Log.d(TAG, "Activity dispatchTouchEvent MOVE")
            MotionEvent.ACTION_UP -> Log.d(TAG, "Activity dispatchTouchEvent UP")
            MotionEvent.ACTION_CANCEL -> Log.d(TAG, "Activity dispatchTouchEvent CANCEL")
        }
        val b = super.dispatchTouchEvent(ev)
        Log.d(TAG, "Activity dispatchTouchEvent RETURNS " + b)
        return b
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when (event.getActionMasked()) {
            MotionEvent.ACTION_DOWN -> Log.d(TAG, "Activity onTouch DOWN")
            MotionEvent.ACTION_MOVE -> Log.d(TAG, "Activity onTouch MOVE")
            MotionEvent.ACTION_UP -> Log.d(TAG, "Activity onTouch UP")
            MotionEvent.ACTION_CANCEL -> Log.d(TAG, "Activity onTouch CANCEL")
        }
        val b = super.onTouchEvent(event)
        Log.d(TAG, "Activity onTouchEvent RETURNS " + b)
        return b
    }
    companion object {
        val TAG = "SubActivity"
    }
}