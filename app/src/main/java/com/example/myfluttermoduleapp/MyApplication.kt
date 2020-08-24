package com.example.myfluttermoduleapp

import android.app.Application
import com.example.myfluttermoduleapp.helper.*

class MyApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        ResourceHelper.init(this)
        AppActivityHelper.init()
        FlutterEngineHelper.init()
        MixRouteHelper.init()
        MethodChannelHelper.init()
    }

}