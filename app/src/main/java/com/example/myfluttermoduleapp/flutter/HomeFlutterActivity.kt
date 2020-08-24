package com.example.myfluttermoduleapp.flutter

import com.example.myfluttermoduleapp.helper.FlutterRouteHelper
import com.example.myfluttermoduleapp.helper.MixRouteHelper
import io.flutter.embedding.android.FlutterSurfaceView

class HomeFlutterActivity : BaseFlutterActivity() {
    var flutterSurfaceView: FlutterSurfaceView? = null

    companion object {
        const val route = "flutter/home"
    }

    override fun getFlutterRoute(): String {
        return route
    }

    override fun onFlutterSurfaceViewCreated(flutterSurfaceView: FlutterSurfaceView) {
        super.onFlutterSurfaceViewCreated(flutterSurfaceView)
        this.flutterSurfaceView = flutterSurfaceView
    }

    override fun onResume() {
        super.onResume()
    }

}