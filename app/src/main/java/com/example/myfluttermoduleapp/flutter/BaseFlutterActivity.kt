package com.example.myfluttermoduleapp.flutter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myfluttermoduleapp.helper.FlutterRouteHelper
import com.example.myfluttermoduleapp.helper.MixRouteHelper
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs.BackgroundMode
import io.flutter.embedding.android.FlutterSurfaceView
import io.flutter.embedding.android.FlutterTextureView
import java.util.concurrent.atomic.AtomicInteger


abstract class BaseFlutterActivity : FlutterActivity(), IFlutterContainer {
    private var flutterSurfaceView: FlutterSurfaceView? = null
    private var actKey = 0
    private var curUrl = ""
    private val mTag: String
        get() = "$actKey-${this.javaClass.simpleName}-${this.hashCode()}"

    private var isClearTop: Boolean = false;

    companion object {
        private val seq = AtomicInteger(0)
        private const val ACTIVITY_KEY = "flutter_act_key"
        const val URL = "flutterUrl"
        const val CLEAR_TOP = "flutterClearTop"
        fun buildIntent(clazz: Class<out FlutterActivity>, context: Context): Intent {
            return TTCachedEngineIntentBuilder(clazz).backgroundMode(BackgroundMode.opaque)
                .build(context)
        }
    }

    override fun getUrl(): String {
        return curUrl
    }

    override fun getKey(): Int {
        return actKey
    }

    override fun onFlutterSurfaceViewCreated(flutterSurfaceView: FlutterSurfaceView) {
        Log.i(mTag, "onFlutterSurfaceViewCreated")
        this.flutterSurfaceView = flutterSurfaceView

    }

    override fun onFlutterTextureViewCreated(flutterTextureView: FlutterTextureView) {
        Log.i(mTag, "onFlutterTextureViewCreated")

    }

//    override fun getRenderMode(): RenderMode {
////        return RenderMode.surface
////    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKey("onCreate", savedInstanceState)
        initUrl("onCreate")
        route("onCreate")
    }

    private fun initUrl(caller: String, newIntent: Intent? = null) {
        curUrl = if (newIntent != null) {
            newIntent.getStringExtra(URL) ?: ""
        } else {
            intent.getStringExtra(URL) ?: ""
        }
        Log.i(mTag, "$caller-initUrl $curUrl")
    }

    private fun initKey(caller: String, savedInstanceState: Bundle? = null) {
        val lastKey = savedInstanceState?.getInt(ACTIVITY_KEY) ?: 0
        actKey = if (lastKey > 0) {
            lastKey
        } else {
            seq.incrementAndGet()
        }
        Log.i(mTag, "$caller-initKey actKey $actKey, lastKey $lastKey")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ACTIVITY_KEY, actKey)
    }

    override fun onFlutterUiDisplayed() {
        super.onFlutterUiDisplayed()
        Log.i(mTag, "onFlutterUiDisplayed")
    }

    override fun onFlutterUiNoLongerDisplayed() {
        super.onFlutterUiNoLongerDisplayed()
        Log.i(mTag, "onFlutterUiNoLongerDisplayed")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        initUrl("onNewIntent", intent)
        route("onNewIntent", intent)
    }

    private fun route(caller: String, newIntent: Intent? = null) {

        val clearTop =
            newIntent?.getBooleanExtra(CLEAR_TOP, false) ?: intent.getBooleanExtra(CLEAR_TOP, false)

        Log.i(mTag, "$caller clearTop $clearTop, flutterRoute $curUrl")

        MixRouteHelper.pushFlutter(getKey(), getUrl(), clearTop = clearTop)
    }

    abstract fun getFlutterRoute(): String

    override fun onResume() {
        super.onResume()
        if (isClearTop) {
            flutterEngine?.renderer
        }
        if (!isClearTop) {
            isClearTop = true
        }

        Log.i(mTag, "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.i(mTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(mTag, "onStop")
    }

    override fun finish() {
        super.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(mTag, "onDestroy")
    }

    override fun onBackPressed() {
        FlutterRouteHelper.pop(getKey())
    }
}