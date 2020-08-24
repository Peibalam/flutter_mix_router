package com.example.myfluttermoduleapp.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import java.lang.StringBuilder
import java.lang.ref.WeakReference

object AppActivityHelper {
    
    private const val mTag = "AppActivityHelper"

    //要记得释放不然会导致内存泄露
    var topActivity: WeakReference<Activity>? = null
        private set

    //第一个启动的Activity
    var appFirstStartActivity = ""
        private set

    var activityList: ArrayList<WeakReference<Activity>> = ArrayList()

    fun init() {
        ResourceHelper.application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                val name = activity.javaClass.simpleName
                Log.i(mTag, "$name onActivityCreated ${activity.hashCode()}")
                val topAct = WeakReference(activity)
                topActivity = topAct
                activityList.add(topAct)
                if ("" == appFirstStartActivity) {
                    appFirstStartActivity = name
                    Log.i(mTag, "appFirstStartActivity $name ${activity.hashCode()}")
                }
            }

            override fun onActivityPaused(p0: Activity) {

            }

            override fun onActivityStarted(activity: Activity) {
                if (topActivity?.get()?.hashCode() != activity.hashCode()) {
                    topActivity = WeakReference(activity)
                    Log.i(mTag, "onActivityStarted+${topActivity.hashCode()}")
                }
            }

            override fun onActivityResumed(activity: Activity) {
                Log.i(mTag, "onActivityResumed ${activity.javaClass.simpleName} ${activity.hashCode()}")
                if (topActivity?.get()?.hashCode() != activity.hashCode()) {
                    topActivity = WeakReference(activity)
                }
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.i(mTag, "onActivityDestroyed ${activity.javaClass.simpleName} ${activity.hashCode()}, top ${topActivity?.get()?.javaClass?.simpleName} ${topActivity?.get()?.hashCode()}")
                if (topActivity?.get()?.hashCode() == activity.hashCode()) {
                    topActivity = null
                }

                removeActivityList(activity)

            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

            }

            override fun onActivityStopped(p0: Activity) {

            }


        })
    }

    private fun removeActivityList(activity: Activity) {

        if (activityList.isEmpty()) {
            return
        }

        for (index in activityList.size - 1 downTo 0) {
            val name = activityList[index].get()?.javaClass?.simpleName
            if (name == activity.javaClass.simpleName) {
                activityList.removeAt(index)
                break
            }
        }

        val str = StringBuilder()

        activityList.forEach {
            str.append("act ${it.get()?.javaClass?.simpleName} taskId ${it.get()?.taskId}, hc ${it.get()?.hashCode()}, ")
        }

        Log.i(mTag, "removeActivityList $str")
    }

}