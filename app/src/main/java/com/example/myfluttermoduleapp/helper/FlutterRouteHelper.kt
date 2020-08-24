package com.example.myfluttermoduleapp.helper

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.SparseArray
import androidx.core.util.contains
import androidx.core.util.isEmpty
import com.example.myfluttermoduleapp.flutter.BaseFlutterActivity
import com.example.myfluttermoduleapp.flutter.HomeFlutterActivity


/**
 * flutter路由
 */
object FlutterRouteHelper {

    private val routeController = FlutterRouteController()


    fun push(key: Int, url: String, clearTop: Boolean = false): FlutterRouteInfo {
        return routeController.put(key, url, clearTop)
    }

    fun pop(key: Int) {
        routeController.pop(key)
    }


    private class FlutterRouteController {

        private val flutterRoutes = SparseArray<ArrayList<FlutterRouteInfo>>()

        private val mTag = "FlutterRouteController"

        init {
            ResourceHelper.application.registerActivityLifecycleCallbacks(object :
                Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity) {

                }

                override fun onActivityStarted(activity: Activity) {

                }

                override fun onActivityDestroyed(activity: Activity) {
                    if (activity is BaseFlutterActivity) {
                        popAllIfNeed(activity.getKey())
                    }
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

                }

                override fun onActivityStopped(activity: Activity) {

                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

                }

                override fun onActivityResumed(activity: Activity) {

                }
            })
        }


        fun put(key: Int, url: String, clearTop: Boolean = false): FlutterRouteInfo {

            val fixKey = if (clearTop) {
                getClearTopFixKey(curKey = key, targetUrl = url)
            } else {
                key
            }

            val info: FlutterRouteInfo

            if (flutterRoutes.contains(fixKey)) {
                val list = flutterRoutes.get(fixKey)

                if (clearTop) {
                    list?.forEach { route ->
                        if (route.url == url) {
                            return route
                        }
                    }
                }

                info = FlutterRouteInfo(key = fixKey, url = url, index = list.size)
                list.add(info)
            } else {
                info = FlutterRouteInfo(key = fixKey, url = url, index = 0)
                flutterRoutes.put(fixKey, ArrayList<FlutterRouteInfo>().apply {
                    add(info)
                })
            }

            Log.i(mTag, "push key $key, fixKey $fixKey, clearTop $clearTop, $info")

            return info
        }

        fun pop(key: Int): FlutterRouteInfo? {

            val routeList: ArrayList<FlutterRouteInfo>? = flutterRoutes.get(key)

            var removedInfo: FlutterRouteInfo? = null

            if (routeList?.isNotEmpty() == true) {
                removedInfo = routeList.removeAt(routeList.size - 1)
                MixRouteHelper.popFlutter(key, listOf(removedInfo))
            }

            if (routeList?.isEmpty() == true) {
                val act = AppActivityHelper.topActivity?.get()
                Log.i(mTag, "flutterRouteIsEmpty finish ${act?.javaClass?.simpleName}")
                flutterRoutes.remove(key)
                act?.finish()
            }

            Log.i(mTag, "pop $key, info $removedInfo")

            return removedInfo
        }

        private fun getClearTopFixKey(curKey: Int, targetUrl: String): Int {
            Log.i(mTag, "getClearTopFixKey curKey $curKey, targetUrl $targetUrl")

            if (flutterRoutes.isEmpty()) {
                return curKey
            }

            for (index in flutterRoutes.size() - 1 downTo 0) {
                val eachKey = flutterRoutes.keyAt(index)
                flutterRoutes.get(eachKey)?.forEach { info ->
                    if (info.url == targetUrl) {
                        handleClearTopImpl(
                            targetKey = eachKey, curKey = curKey,
                            targetUrl = targetUrl
                        )
                        return eachKey
                    }
                }
            }

            Log.i(mTag, "noNeedClearTop")
            return curKey
        }

        private fun handleClearTopImpl(targetKey: Int, curKey: Int, targetUrl: String) {
            Log.i(mTag, "handleClearTopImpl targetKey $targetKey, curKey $curKey")
            if (targetKey == curKey) {
                clearTopByKeyAndUrl(targetKey, targetUrl)
            } else {
                handleMixRouteClearTop(curKey, targetKey, targetUrl)
            }
        }

        private fun handleMixRouteClearTop(
            curKey: Int,
            targetKey: Int,
            targetUrl: String
        ) {
            var taskId = -1
            AppActivityHelper.activityList.reversed().forEach { weakReference ->
                val act = weakReference.get()
                if (act is BaseFlutterActivity) {
                    if (act.getKey() == curKey) {
                        taskId = act.taskId
                        popAllIfNeed(act.getKey())
                        act.finish()

                        Log.i(mTag, "handleClearTopImpl taskId $taskId")
                    } else if (act.getKey() == targetKey) {
                        //结束循环
                        clearTopByKeyAndUrl(targetKey = targetKey, targetUrl = targetUrl)
//                        Handler(Looper.getMainLooper()).postDelayed({
//                            act.startActivity(BaseFlutterActivity.buildIntent(HomeFlutterActivity::class.java, act).apply {
//                                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                                putExtra(BaseFlutterActivity.CLEAR_TOP, true)
//                                putExtra(BaseFlutterActivity.URL, targetKey)
//                            })
//                        }, 1000)

                        Log.i(mTag, "handleClearTopImpl finish")
                        return
                    }
                } else if (act is Activity && act.taskId == taskId) {
                    act.finish()
                } else {
                    Log.i(mTag, "ignore ${act?.javaClass?.simpleName}, taskId $taskId")
                }
            }
        }

        private fun clearTopByKeyAndUrl(
            targetKey: Int,
            targetUrl: String
        ) {
            val list = flutterRoutes[targetKey]

            if (list == null || list.isEmpty()) {
                Log.i(mTag, "handleClearTopImpl list is empty")
                return
            }

            val removeList = ArrayList<FlutterRouteInfo>()

            for (index in list.size - 1 downTo 0) {
                val tempInfo = list.removeAt(index)
                if (tempInfo.url == targetUrl) {
                    list.add(tempInfo)
                    Log.i(mTag, "handleClearTopImpl pop key $targetKey, $removeList")
                    MixRouteHelper.popFlutter(targetKey, removeList)
                    return
                }
                removeList.add(tempInfo)
            }
        }

        private fun popAllIfNeed(key: Int) {
            if (flutterRoutes.contains(key)) {
                val list = flutterRoutes.get(key)
                Log.i(mTag, "popAll key $key, $list")
                MixRouteHelper.popFlutter(key, list)
                flutterRoutes.remove(key)
            }
        }

    }

}
