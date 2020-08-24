package com.example.myfluttermoduleapp.flutter

class ThreeFlutterActivity: BaseFlutterActivity() {

    companion object {
        const val route = "flutter/three"
    }

    override fun getFlutterRoute(): String {
        return route
    }

}