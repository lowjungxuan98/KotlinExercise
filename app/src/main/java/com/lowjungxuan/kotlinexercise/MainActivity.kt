package com.lowjungxuan.kotlinexercise

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.lowjungxuan.kotlinexercise.databinding.ActivityMainBinding
import com.lowjungxuan.kotlinexercise.student.data.StudentList
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
                R.id.profile -> replaceFragment(Profile())
                R.id.settings -> replaceFragment(Settings())
                else -> {

                }
            }
            true
        }

        SocketHandler.setSocket()
        SocketHandler.establishConnection()
        val mSocket = SocketHandler.getSocket()
        mSocket.on("data") {
            if (it[0] != null) {
                this.runOnUiThread {
                    val studentList = Gson().fromJson(it[0].toString(), StudentList::class.java)
//                    Log.e("Socket IO Data",it[0].toString())
//                    Log.e("Socket IO Data",it[0]::class.java.typeName)
                    Log.e("Socket IO Data",studentList.toString())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}