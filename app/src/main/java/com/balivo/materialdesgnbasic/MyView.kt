package com.balivo.materialdesgnbasic

/**
 * Created by balivo on 3/23/2018.
 */
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView

class MyView : TextView {
    internal lateinit var paint: Paint

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> Log.d(TAG, "View dispatchTouchEvent DOWN")
            MotionEvent.ACTION_MOVE -> Log.d(TAG, "View dispatchTouchEvent MOVE")
            MotionEvent.ACTION_UP -> Log.d(TAG, "View dispatchTouchEvent UP")
            MotionEvent.ACTION_CANCEL -> Log.d(TAG, "View dispatchTouchEvent CANCEL")
        }
        val b = super.dispatchTouchEvent(event)
        Log.d(TAG, "View dispatchTouchEvent RETURNS $b")
        return b
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        // Block Touch Events (onInterceptTouchEvent) from parent!!!
        getParent().requestDisallowInterceptTouchEvent(true)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> Log.d(TAG, "View onTouchEvent DOWN")
            MotionEvent.ACTION_MOVE ->

                Log.d(TAG, "View onTouchEvent MOVE")
            MotionEvent.ACTION_UP -> Log.d(TAG, "View onTouchEvent UP")
            MotionEvent.ACTION_CANCEL -> Log.d(TAG, "View onTouchEvent CANCEL")
        }
//        val b = super.onTouchEvent(event)
        val b = true
        Log.d(TAG, "View onTouchEvent RETURNS $b")
        return b
    }

    fun init() {
        paint = Paint()
        paint.isAntiAlias = true
    }

    companion object {

        val TAG = "MyView"
    }
}