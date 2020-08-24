package com.example.myfluttermoduleapp.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.myfluttermoduleapp.*
import com.example.myfluttermoduleapp.flutter.BaseFlutterActivity
import com.example.myfluttermoduleapp.flutter.HomeFlutterActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

/**
 * flutter 与 native 混合路由
 */
object MixRouteHelper {

    private const val mTag = "MixRouteHelper"

    private const val MIX_ROUTE_NAME = "samples.flutter.dev/ttMixRoute"
    private const val KEY_ROUTE_INFO = "flutterRouteInfo"
    private const val METHOD_MIX_ROUTE = "mixRoute"
    private const val METHOD_PUSH_FLUTTER = "pushFlutter"
    private const val METHOD_CLOSE_FLUTTER_PAGE = "closeFlutterPage"
    private const val METHOD_POP_FLUTTER = "popFlutter"

    private val methodChannel = MethodChannel(FlutterEngineHelper.getBinaryMessenger(), MIX_ROUTE_NAME)

    fun init() {
        methodChannel.setMethodCallHandler { call, result ->
            handleMixRouteChannel(call, result)
        }

    }

    private fun handleMixRouteChannel(call: MethodCall, result: MethodChannel.Result) {

        when(call.method) {
            METHOD_MIX_ROUTE -> {
                val map = call.arguments
                Log.i(mTag, "mix route $map")
                if (map is Map<*, *>) {
                    val url = map["url"]
                    val clearTop = if (map["clearTop"] is Boolean) {
                        map["clearTop"] as Boolean
                    } else {
                        false
                    }
                    if (url is String && url.isNotEmpty()) {
                        routeImpl(url, clearTop)
                    }

                }
            }

            METHOD_CLOSE_FLUTTER_PAGE -> {
                Log.i(mTag, "closeFlutterPage act ${AppActivityHelper.topActivity?.get()?.javaClass?.simpleName}")
                AppActivityHelper.topActivity?.get()?.finish()
            }

            else -> {
                Log.w(mTag, "unSupportMethod ${call.method}")
            }
        }

    }

    fun routeImpl(url: String, clearTop: Boolean = false) {
        val act = AppActivityHelper.topActivity?.get()
        Log.i(mTag, "routeImpl ${act?.javaClass?.simpleName}, clearTop $clearTop")

        if (act == null) {
            return
        }

        when(url) {
            MainActivity.route -> {
                startNativeAct(act, MainActivity::class.java, clearTop)
            }

            SecondActivity.route -> {
                startNativeAct(act, SecondActivity::class.java, clearTop)
            }

            ThreeActivity.route -> {
                startNativeAct(act, ThreeActivity::class.java, clearTop)
            }

            FourActivity.route -> {
                startNativeAct(act, FourActivity::class.java, clearTop)
            }

            FiveActivity.route -> {
                startNativeAct(act, FiveActivity::class.java, clearTop)
            }

            SixActivity.route -> {
                startNativeAct(act, SixActivity::class.java, clearTop)
            }

//            HomeFlutterActivity.route -> {
//                act.startActivity(BaseFlutterActivity.buildIntent(HomeFlutterActivity::class.java, act))
//            }
//            FirstFlutterActivity.route -> {
//                act.startActivity(BaseFlutterActivity.buildIntent(FirstFlutterActivity::class.java, act))
//            }
//            SecondFlutterActivity.route -> {
//                act.startActivity(BaseFlutterActivity.buildIntent(SecondFlutterActivity::class.java, act))
//            }
//            ThreeFlutterActivity.route -> {
//                act.startActivity(BaseFlutterActivity.buildIntent(ThreeFlutterActivity::class.java, act))
//            }
//            FourFlutterActivity.route -> {
//                act.startActivity(BaseFlutterActivity.buildIntent(FourFlutterActivity::class.java, act))
//            }
//            FiveFlutterActivity.route -> {
//                act.startActivity(BaseFlutterActivity.buildIntent(FiveFlutterActivity::class.java, act))
//            }
//            SixFlutterActivity.route -> {
//                act.startActivity(BaseFlutterActivity.buildIntent(SixFlutterActivity::class.java, act))
//            }

            else -> {

                if (clearTop && act is BaseFlutterActivity) {
                    pushFlutter(key = act.getKey(), url = url, clearTop = clearTop, delay = 1000L)
                    return
                }

                act.startActivity(BaseFlutterActivity.buildIntent(HomeFlutterActivity::class.java, act).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    if (clearTop) {
                        putExtra(BaseFlutterActivity.CLEAR_TOP, true)
                    }
                    putExtra(BaseFlutterActivity.URL, url)
                })
                Log.i(mTag, "unSupport url $url")
            }
        }
    }

    private fun startNativeAct(context: Context, clazz: Class<out Activity>, clearTop: Boolean) {
        context.startActivity(
            Intent(context, clazz).apply {
                if (clearTop) {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
            })
    }

    fun pushFlutter(key: Int, url: String, clearTop: Boolean = false, delay: Long = 0) {
        val info = FlutterRouteHelper.push(key, url, clearTop = clearTop)
        if (delay > 0) {
//            Handler(Looper.getMainLooper()).postDelayed({
//                methodChannel.invokeMethod(
//                    METHOD_PUSH_FLUTTER,
//                    GsonUtil.toJson(
//                        mapOf(
//                            KEY_ROUTE_INFO to listOf(info)
//                        )
//                    )
//                )
//            }, delay)
        } else {
            methodChannel.invokeMethod(
                METHOD_PUSH_FLUTTER,
                GsonUtil.toJson(
                    mapOf(
                        KEY_ROUTE_INFO to listOf(info)
                    )
                )
            )
        }
    }

    fun popFlutter(key: Int, urls: List<FlutterRouteInfo>) {

        urls.sortedWith(Comparator { routeInfo1, routeInfo2 ->
            if (routeInfo1.key == routeInfo2.key && routeInfo1.index == routeInfo2.index) {
                0
            } else if (routeInfo1.key > routeInfo2.key || routeInfo1.key == routeInfo2.key && routeInfo1.index > routeInfo2.index) {
                1
            } else {
                -1
            }
        })

        methodChannel.invokeMethod(
            METHOD_POP_FLUTTER,
            GsonUtil.toJson(
                mapOf(
                    KEY_ROUTE_INFO to urls
                )
            )
        )
    }

}