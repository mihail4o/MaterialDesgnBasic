package com.balivo.materialdesgnbasic

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import it.neokree.materialtabs.MaterialTab
import it.neokree.materialtabs.MaterialTabHost
import it.neokree.materialtabs.MaterialTabListener
import com.balivo.materialdesgnbasic.MainActivity.MyFragment

class ActivityUsingTabLibrary : AppCompatActivity(), MaterialTabListener {

    private lateinit var mToolbar:Toolbar

    private lateinit var mTabHost:MaterialTabHost
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_using_tab_library)

        mToolbar = findViewById(R.id.app_bar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mTabHost = findViewById(R.id.materialTabHost) as MaterialTabHost
        mViewPager = findViewById(R.id.viewPager) as ViewPager

        val adapter = ViewPagerAdapter(supportFragmentManager)
        mViewPager!!.adapter = adapter
        mViewPager!!.setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                mTabHost!!.setSelectedNavigationItem(position)
            }
        })
        // insert all tabs from pagerAdapter data
        for (i in 0 until adapter.count)
        {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setIcon(adapter.getIcon(i))
                            .setTabListener(this)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu):Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_using_tab_library, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onTabReselected(tab: MaterialTab?) {

    }

    override fun onTabUnselected(tab: MaterialTab?) {

    }

    override fun onTabSelected(materialTab: MaterialTab) {
        mViewPager!!.currentItem = materialTab.position
    }

    internal inner class ViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

        //var tabText = resources.getStringArray(R.array.tabs)
        var icons= intArrayOf(R.drawable.ic_action_home, R.drawable.ic_action_articles, R.drawable.ic_action_personal)

        override fun getItem(position: Int): Fragment {

            return  MyFragment.getInstance(position)
        }

        override fun getPageTitle(position: Int): CharSequence {

            return resources.getStringArray(R.array.tabs)[position]
        }

        fun getIcon(position:Int): Drawable {
            return resources.getDrawable(icons[position])
        }

        override fun getCount(): Int {
            return 3
        }

    }
}
