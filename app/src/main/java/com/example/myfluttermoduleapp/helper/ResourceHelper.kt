package com.example.myfluttermoduleapp.helper

import android.app.Application

object ResourceHelper {

    private var mApplication: Application? = null

    val application: Application
        get() = mApplication!!

    fun init(app: Application) {
        mApplication = app
    }

}