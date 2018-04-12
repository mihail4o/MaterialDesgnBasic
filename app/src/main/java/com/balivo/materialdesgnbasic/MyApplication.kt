package com.balivo.materialdesgnbasic

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        var instance: MyApplication? = null
            private set

        val appContext: Context
            get() = instance!!.applicationContext
    }
}