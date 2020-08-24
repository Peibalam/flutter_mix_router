package com.example.myfluttermoduleapp.flutter

class FirstFlutterActivity: BaseFlutterActivity() {

    companion object {
        const val route ="flutter/first"
    }

    override fun getFlutterRoute(): String {
        return route
    }

}