package com.lowjungxuan.kotlinexercise

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lowjungxuan.kotlinexercise.content_provider.Settings
import com.lowjungxuan.kotlinexercise.databinding.ActivityMainBinding
import com.lowjungxuan.kotlinexercise.service.ServiceFragment
import com.lowjungxuan.kotlinexercise.student.presentation.StudentFragment
import com.lowjungxuan.kotlinexercise.utils.SocketHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(StudentFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(StudentFragment())
                R.id.profile -> replaceFragment(ServiceFragment())
                R.id.settings -> replaceFragment(Settings())
                else -> {

                }
            }
            true
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