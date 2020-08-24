package com.example.myfluttermoduleapp.helper


import android.util.Log
import io.flutter.plugin.common.MethodChannel

object MethodChannelHelper {

    private const val mTag = "MethodChannelHelper"

    fun init() {
        MethodChannel(
            FlutterEngineHelper.getBinaryMessenger(),
            "samples.flutter.dev/log"
        ).setMethodCallHandler { call, result ->
            Log.i(mTag, "${call.arguments}")
        }
    }

}