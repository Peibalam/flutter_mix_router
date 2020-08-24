package com.example.myfluttermoduleapp.flutter

class SecondFlutterActivity: BaseFlutterActivity() {

    companion object {
        const val route = "flutter/second"
    }

    override fun getFlutterRoute(): String {
        return route
    }

}