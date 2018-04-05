package com.balivo.materialdesgnbasic

import android.R.menu
import android.content.Intent
import android.support.v7.app.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.balivo.materialdesgnbasic.tabs.SlidingTabLayout


class MainActivity : AppCompatActivity() {

    private var toolbar:Toolbar?=null

    private lateinit var mPager : ViewPager
    private lateinit var mTabs : SlidingTabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar=findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)

        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)

        val drawerFragment:NavigationDrawerFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer)
                as NavigationDrawerFragment

        drawerFragment.setUp(R.id.fragment_navigation_drawer, findViewById(R.id.drawer_layout)as DrawerLayout, toolbar as Toolbar)

        mPager = findViewById(R.id.pager) as ViewPager
        mPager.setAdapter(MyPagerAdapter(getSupportFragmentManager()))
        mTabs = findViewById(R.id.tabs) as SlidingTabLayout
        mTabs.setViewPager(mPager)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    class MyFragment:Fragment() {

        private lateinit var textView : TextView

        companion object {

            fun getInstance(position:Int):MyFragment {

                val myFragment = MyFragment()
                val args = Bundle()
                args.putInt("position", position)
                myFragment.setArguments(args)

                return myFragment
            }
        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val layout = inflater!!.inflate(R.layout.fragment_my, container, false)

            textView = layout.findViewById(R.id.position) as TextView

            val bundle = arguments

            if (bundle!=null) {
                textView.text= "The Page Selected Is " + bundle.getInt("position").toString()
            }

            return layout
        }
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

    internal inner class MyPagerAdapter : FragmentPagerAdapter {

        lateinit var tabs:Array<String>

        init{
            tabs = getResources().getStringArray(R.array.tabs)
        }

        constructor(fm : FragmentManager): super(fm) {}

        override fun getPageTitle(position: Int): CharSequence {
            return tabs[position]
        }

        override fun getItem(position: Int): Fragment {
            val myFragment = MyFragment.getInstance(position)
            return myFragment
        }

        override fun getCount(): Int {
            return 3
        }

    }
}