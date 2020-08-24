package com.example.myfluttermoduleapp.flutter

class FiveFlutterActivity: BaseFlutterActivity() {

    companion object {
        const val route = "flutter/five"
    }

    override fun getFlutterRoute(): String {
        return route
    }

}