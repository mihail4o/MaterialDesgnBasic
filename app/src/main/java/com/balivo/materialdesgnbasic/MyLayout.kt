package com.balivo.materialdesgnbasic

/**
 * Created by balivo on 3/23/2018.
 */
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

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

        paint!!.isAntiAlias = true
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
        paint!!.color = Color.GRAY
        canvas.drawRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), paint!!)
    }

    companion object {

        val TAG = "MyLayout"
    }
}