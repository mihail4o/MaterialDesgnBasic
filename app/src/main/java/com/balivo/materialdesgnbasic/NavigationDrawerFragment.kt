package com.balivo.materialdesgnbasic


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class NavigationDrawerFragment : Fragment() {

    private var mDrawerToggle:ActionBarDrawerToggle?=null
    private var mDrawerLayout:DrawerLayout?=null

    private var mUserLernedDrawer:Boolean?=false
    private var mFromSavedInstanceState:Boolean?=false

    private var containerView:View?=null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_navigation_drawer, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mUserLernedDrawer = readFromPreferences(getActivity(), KEY_USER_LERNED_DRAWER,"false").toBoolean()

        if (savedInstanceState != null) {
            mFromSavedInstanceState = true
        }

    }

    fun setUp(fragmentID:Int, drawerLayout:DrawerLayout, toolbar:Toolbar) {

        containerView = getActivity().findViewById(fragmentID)

        mDrawerLayout = drawerLayout
        mDrawerToggle = object:ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)

                if (!mUserLernedDrawer!!){
                    mUserLernedDrawer = true
                    saveToPreferences(getActivity(), KEY_USER_LERNED_DRAWER, mUserLernedDrawer.toString())
                    getActivity().invalidateOptionsMenu()
                }
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                getActivity().invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {

                // Log.d("BALIVO"," offset: "+ slideOffset)
                // Dimm the toolbar
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset)
                }
            }
        }

        if (!mUserLernedDrawer!! && !mFromSavedInstanceState!!){

            mDrawerLayout!!.openDrawer(containerView)

        }

        mDrawerLayout!!.addDrawerListener(mDrawerToggle as ActionBarDrawerToggle)

        mDrawerLayout!!.post(object :Runnable {

            override fun run() {
                mDrawerToggle!!.syncState()
            }

        })
    }

    companion object {
        val PREP_FILE_NAME = "testpref"
        val KEY_USER_LERNED_DRAWER = "user_lerned_drawer"

        fun saveToPreferences(context:Context, preferenceName:String, preferenceValue:String){

            var sharedPreferences:SharedPreferences = context.getSharedPreferences(PREP_FILE_NAME,Context.MODE_PRIVATE)
            var editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(preferenceName,preferenceValue)
            editor.apply()
        }

        fun readFromPreferences(context:Context, preferenceName:String, defaultValue:String):String{

            var sharedPreferences:SharedPreferences = context.getSharedPreferences(PREP_FILE_NAME,Context.MODE_PRIVATE)
            return sharedPreferences.getString(preferenceName,defaultValue)

        }

    }

}// Required empty public constructor
