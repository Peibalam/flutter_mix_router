package com.example.myfluttermoduleapp.flutter

import com.example.myfluttermoduleapp.helper.FlutterEngineHelper
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

class TTCachedEngineIntentBuilder(clazz: Class<out FlutterActivity>):
    FlutterActivity.CachedEngineIntentBuilder(clazz, FlutterEngineHelper.ENGINE_ID) {

//    init {
//        this.backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
//    }

}