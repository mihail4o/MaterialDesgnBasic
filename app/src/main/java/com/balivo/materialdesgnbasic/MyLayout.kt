package com.balivo.materialdesgnbasic

/**
 * Created by balivo on 3/23/2018.
 */
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

/*
    STEPS TO HANDLE THE RECYCLER CLICK
    1 Create a class that EXTENDS RecylcerView.OnItemTouchListener
    2 Create an interface inside that class that supports click and long click and indicates the View that was clicked and the position where it was clicked
    3 Create a GestureDetector to detect ACTION_UP single tap and Long Press events
    4 Return true from the singleTap to indicate your GestureDetector has consumed the event.
    5 Find the childView containing the coordinates specified by the MotionEvent and if the childView is not null and the listener is not null either, fire a long click event
    6 Use the onInterceptTouchEvent of your RecyclerView to check if the childView is not null, the listener is not null and the gesture detector consumed the touch event
    7 if above condition holds true, fire the click event
    8 return false from the onInterceptedTouchEvent to give a chance to the childViews of the RecyclerView to process touch events if any.
    9 Add the onItemTouchListener object for our RecyclerView that uses our class created in step 1
     */

class MyLayout : FrameLayout {
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

    fun init() {
        paint = Paint()

        paint.isAntiAlias = true
        setWillNotDraw(false)

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> Log.d(TAG, "MyLayout dispatchTouchEvent DOWN")
            MotionEvent.ACTION_MOVE -> Log.d(TAG, "MyLayout dispatchTouchEvent MOVE")
            MotionEvent.ACTION_UP -> Log.d(TAG, "MyLayout dispatchTouchEvent UP")
            MotionEvent.ACTION_CANCEL -> Log.d(TAG, "MyLayout dispatchTouchEvent CANCEL")
        }
        val b = super.dispatchTouchEvent(ev)
        Log.d(TAG, "MyLayout dispatchTouchEvent RETURNS $b")
        return b
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val b = super.onInterceptTouchEvent(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> Log.d(TAG, "MyLayout onInterceptTouchEvent DOWN")
            MotionEvent.ACTION_MOVE -> Log.d(TAG, "MyLayout onInterceptTouchEvent MOVE")
//            MotionEvent.ACTION_MOVE -> {
//                Log.d(TAG, "MyLayout onInterceptTouchEvent MOVE")
//                return true
//            }
            MotionEvent.ACTION_UP -> Log.d(TAG, "MyLayout onInterceptTouchEvent UP")
            MotionEvent.ACTION_CANCEL -> Log.d(TAG, "MyLayout onInterceptTouchEvent CANCEL")
        }

//        val b = true
        Log.d(TAG, "MyLayout onInterceptTouchEvent RETURNS $b")
        return b
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> Log.d(TAG, "MyLayout onTouchEvent DOWN")
            MotionEvent.ACTION_MOVE -> Log.d(TAG, "MyLayout onTouchEvent MOVE")
            MotionEvent.ACTION_UP -> Log.d(TAG, "MyLayout onTouchEvent UP")
            MotionEvent.ACTION_CANCEL -> Log.d(TAG, "MyLayout onTouchEvent CANCEL")
        }
         val b = super.onTouchEvent(event)
//        val b = true
        Log.d(TAG, "MyLayout onTouchEvent RETURNS $b")
        return b
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = Color.GRAY
        canvas.drawRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), paint)
    }

    companion object {

        val TAG = "MyLayout"
    }

}