package com.example.myfluttermoduleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.myfluttermoduleapp.flutter.BaseFlutterActivity
import com.example.myfluttermoduleapp.flutter.HomeFlutterActivity
import com.example.myfluttermoduleapp.helper.AppActivityHelper
import com.example.myfluttermoduleapp.helper.FlutterEngineHelper
import com.example.myfluttermoduleapp.helper.MixRouteHelper
import io.flutter.plugin.common.MethodChannel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.second_main.*
import kotlinx.android.synthetic.main.second_main.openFlutterPager

class SecondActivity : AppCompatActivity() {

    private val mTag = "SecondActivity"

    companion object {
        const val route = "tt://SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        openFlutterPager.setOnClickListener {
            MixRouteHelper.routeImpl(HomeFlutterActivity.route)
        }

        clearTopToMain.setOnClickListener {
            MixRouteHelper.routeImpl(MainActivity.route, clearTop = true)
        }
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
}