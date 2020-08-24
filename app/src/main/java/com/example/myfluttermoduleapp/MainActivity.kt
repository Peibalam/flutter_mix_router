package com.example.myfluttermoduleapp

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.myfluttermoduleapp.flutter.BaseFlutterActivity
import com.example.myfluttermoduleapp.flutter.HomeFlutterActivity
import com.example.myfluttermoduleapp.helper.FlutterEngineHelper
import com.example.myfluttermoduleapp.helper.MixRouteHelper
import io.flutter.plugin.common.MethodChannel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mTag = "MainActivity"

    companion object {
        const val route = "tt://MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        MethodChannel(
            FlutterEngineHelper.flutterEngine.dartExecutor.binaryMessenger,
            "samples.flutter.dev/openNativePage"
        ).setMethodCallHandler { call, result ->
            Log.i(mTag, "openNativePage startSecondAct")
            startActivity(Intent(this, SecondActivity::class.java))
        }

        openFlutterPager.setOnClickListener {
            MixRouteHelper.routeImpl(HomeFlutterActivity.route)
        }



    }

    private fun getBatteryLevel(): Int {
        val batteryLevel: Int
        batteryLevel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            val intent = registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        }

        return batteryLevel
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}