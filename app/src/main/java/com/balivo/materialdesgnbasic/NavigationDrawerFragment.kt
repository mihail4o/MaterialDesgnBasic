package com.balivo.materialdesgnbasic


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.Toast
import org.lucasr.dspec.DesignSpec




/**
 * A simple [Fragment] subclass.
 */
class NavigationDrawerFragment : Fragment() {

    private var mRecyclerView:RecyclerView?=null

    private var mDrawerToggle:ActionBarDrawerToggle?=null
    private var mDrawerLayout:DrawerLayout?=null

    private var mUserLernedDrawer:Boolean?=false
    private var mFromSavedInstanceState:Boolean?=false

    private var containerView:View?=null

    private var adapter:RecycleAdapter?=null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater!!.inflate(R.layout.fragment_navigation_drawer, container, false)
        mRecyclerView = layout.findViewById(R.id.drawerList)

        /*
        *   dspec
        *
        *   A simple way to define and render UI specs on top of your Android UI.
        *
        *   URL: https://github.com/lucasr/dspec
        *
        *
        *   Add this two line to your View!
        val designSpec = DesignSpec.fromResource(layout, R.raw.spec)
        layout.getOverlay().add(designSpec)

        */

        adapter = RecycleAdapter(getActivity(), getData())
        mRecyclerView!!.setAdapter(adapter)
        mRecyclerView!!.setLayoutManager(LinearLayoutManager(getActivity()))

        mRecyclerView!!.addOnItemTouchListener(RecycleTouchListener(getActivity(), mRecyclerView!!, object:ClickListener{

            override fun onClick(view: View, postion: Int) {
                Toast.makeText(getActivity(), "onClick " + postion, Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(view: View, positon: Int) {
                Toast.makeText(getActivity(), "onLongClick " + positon, Toast.LENGTH_SHORT).show()
            }

        }))

        return layout
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

    fun getData() : ArrayList<Information> {

        var data = ArrayList<Information>()

        data.add(Information(R.drawable.ic_number1,"Kalivo"))
        data.add(Information(R.drawable.ic_number2,"Mihail4o"))
        data.add(Information(R.drawable.ic_number3,"Gabcho"))
        data.add(Information(R.drawable.ic_number4,"Choko"))
        data.add(Information(R.drawable.ic_number1,"Kalivo"))
        data.add(Information(R.drawable.ic_number2,"Mihail4o"))
        data.add(Information(R.drawable.ic_number3,"Gabcho"))
        data.add(Information(R.drawable.ic_number4,"Choko"))
        data.add(Information(R.drawable.ic_number1,"Kalivo"))
        data.add(Information(R.drawable.ic_number2,"Mihail4o"))
        data.add(Information(R.drawable.ic_number3,"Gabcho"))
        data.add(Information(R.drawable.ic_number4,"Choko"))
        return data
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

    inner class RecycleTouchListener(context:Context, recyclerView:RecyclerView, clickListener:ClickListener):RecyclerView.OnItemTouchListener {

        private val mGestureDetector:GestureDetector
        private val mClickListener :  ClickListener

        init {
            Log.d("BALIVO", "Constructor invoked")

            this.mClickListener = clickListener
            mGestureDetector = GestureDetector(context, object:GestureDetector.SimpleOnGestureListener(){

                override fun onSingleTapUp(e: MotionEvent?): Boolean {
                    Log.d("BALIVO", "onSingleTapUp "+e)
                    return true
                }

                override fun onLongPress(e: MotionEvent?) {
                    Log.d("BALIVO", "onLongPress "+e)

                    val child = mRecyclerView!!.findChildViewUnder(e!!.getX(),e!!.getY())

                    if (child!=null && clickListener!=null) {
                        clickListener.onLongClick(child, mRecyclerView!!.getChildLayoutPosition(child))
                    }
                }
            })

        }

        override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
            Log.d("BALIVO", "onTouchEvent"+e)
        }

        override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {

            val child = rv!!.findChildViewUnder(e!!.getX(),e!!.getY())

            if (child!=null && mClickListener!=null && mGestureDetector.onTouchEvent(e)) {

                mClickListener.onClick(child, rv.getChildLayoutPosition(child))

            }

            return false
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }

    }

    interface ClickListener {
        fun onClick(view:View, postion:Int)
        fun onLongClick(view:View, positon:Int)
    }

}// Required empty public constructor
