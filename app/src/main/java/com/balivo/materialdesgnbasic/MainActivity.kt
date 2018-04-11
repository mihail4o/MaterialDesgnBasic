package com.balivo.materialdesgnbasic

import android.content.Intent
import android.support.v7.app.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.balivo.materialdesgnbasic.tabs.SlidingTabLayout
import com.balivo.materialdesgnbasic.MyFragment


class MainActivity : AppCompatActivity() {

    private var mToolbar:Toolbar?=null

    private lateinit var mViewPager : ViewPager
    private lateinit var mSlidingTabLayout : SlidingTabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar=findViewById(R.id.app_bar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val drawerFragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_navigation_drawer) as NavigationDrawerFragment

        drawerFragment.setUp(R.id.fragment_navigation_drawer,
                findViewById(R.id.drawer_layout) as DrawerLayout, mToolbar as Toolbar)

        mViewPager = findViewById(R.id.pager) as ViewPager
        mViewPager.setAdapter(MyPagerAdapter(getSupportFragmentManager()))
        mSlidingTabLayout = findViewById(R.id.tabs) as SlidingTabLayout
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab_view, R.id.tabText)
        mSlidingTabLayout.setDistributeEvenly(true)
        // colors for tab
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.primary))
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accent))
        mSlidingTabLayout.setViewPager(mViewPager)
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

        if (id === R.id.action_tab_busing_library) {
            startActivity(Intent(this, ActivityUsingTabLibrary::class.java))
        }

        else if (id == R.id.navigate) {

            startActivity(Intent(this, SubActivity::class.java))


        }
        return super.onOptionsItemSelected(item)
    }

    internal inner class MyPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

        var tabText = resources.getStringArray(R.array.tabs)
        var icons= intArrayOf(R.drawable.ic_action_home, R.drawable.ic_action_articles, R.drawable.ic_action_personal)

        override fun getItem(position: Int): Fragment {
            val myFragment = MyFragment.getInstance(position)
            return myFragment
        }

        override fun getPageTitle(position: Int): CharSequence {

            val drawable = resources.getDrawable(icons[position])
            drawable.setBounds(150, 0, 280, drawable.intrinsicHeight)
            val imageSpan = ImageSpan(drawable)
            val spannableString = SpannableString(" ")
            spannableString.setSpan(imageSpan,0,spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            return spannableString
        }

        override fun getCount(): Int {
            return 3
        }

    }


}