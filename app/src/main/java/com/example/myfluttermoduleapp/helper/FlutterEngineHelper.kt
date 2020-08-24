package com.example.myfluttermoduleapp.helper

import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BinaryMessenger

object FlutterEngineHelper {

    // Instantiate a FlutterEngine.
    val flutterEngine = FlutterEngine(ResourceHelper.application)

    const val ENGINE_ID = "my_engine_id"

    private const val mTag = "FlutterEngineHelper"

    fun init() {
        Log.i(mTag, "initStart")

        // Configure an initial route.
//        flutterEngine.navigationChannel.setInitialRoute("your/route/here")

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(
                ENGINE_ID,
                flutterEngine
            )

        Log.i(mTag, "initEnd")

    }


    fun getBinaryMessenger(): BinaryMessenger {
        return flutterEngine.dartExecutor.binaryMessenger
    }

    fun destroyEngine() {
        flutterEngine.destroy()
    }

}