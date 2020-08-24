package com.example.myfluttermoduleapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.myfluttermoduleapp.flutter.HomeFlutterActivity
import com.example.myfluttermoduleapp.helper.MixRouteHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.openFlutterPager
import kotlinx.android.synthetic.main.second_main.*

class SixActivity : AppCompatActivity() {

    private val mTag = "SixActivity"

    companion object {
        const val route = "tt://SixActivity"
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