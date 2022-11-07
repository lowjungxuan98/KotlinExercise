package com.lowjungxuan.kotlinexercise

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lowjungxuan.kotlinexercise.broadcast_receiver.MyBroadcastReceiver
import com.lowjungxuan.kotlinexercise.content_provider.ContentProviderFragment
import com.lowjungxuan.kotlinexercise.databinding.ActivityMainBinding
import com.lowjungxuan.kotlinexercise.implicit_intent.ImplicitIntentFragment
import com.lowjungxuan.kotlinexercise.service.ServiceFragment
import com.lowjungxuan.kotlinexercise.student.presentation.StudentFragment
import com.lowjungxuan.kotlinexercise.utils.SocketHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var receiver: BroadcastReceiver

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        this.supportActionBar.title
        title = Build.BRAND
        replaceFragment(StudentFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(StudentFragment())
                R.id.provider -> replaceFragment(ServiceFragment())
                R.id.content_provider -> replaceFragment(ContentProviderFragment())
                R.id.implicit_intent -> replaceFragment(ImplicitIntentFragment())
                else -> {

                }
            }
            true
        }
        receiver = MyBroadcastReceiver()
//        android.intent.action.BATTERY_LOW	            Indicates low battery condition on the device.
//        android.intent.action.BOOT_COMPLETED	        This is broadcast once after the system has finished booting
//        android.intent.action.CALL	                To perform a call to someone specified by the data
//        android.intent.action.DATE_CHANGED 	        Indicates that the date has changed
//        android.intent.action.REBOOT	                Indicates that the device has been a reboot
//        android.net.conn.CONNECTIVITY_CHANGE	        The mobile network or wifi connection is changed(or reset)
//        android.intent.ACTION_AIRPLANE_MODE_CHANGED   This indicates that airplane mode has been switched on or off.
        // Intent Filter is useful to determine which apps wants to receive
        // which intents,since here we want to respond to change of
        // airplane mode
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            // registering the receiver
            // it parameter which is passed in  registerReceiver() function
            // is the intent filter that we have just created
            registerReceiver(receiver, it)
        }
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.frame_layout, fragment)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        Log.e("activity life cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("activity life cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("activity life cycle", "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("activity life cycle", "onRestart")
    }

    override fun onStop() {
        super.onStop()
        // since AirplaneModeChangeReceiver class holds a instance of Context
        // and that context is actually the activity context in which
        // the receiver has been created
//        unregisterReceiver(receiver)
        Log.e("activity life cycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("activity life cycle", "onDestroy")
    }

    fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}