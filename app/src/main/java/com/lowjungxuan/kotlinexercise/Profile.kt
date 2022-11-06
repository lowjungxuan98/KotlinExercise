package com.lowjungxuan.kotlinexercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.lowjungxuan.kotlinexercise.databinding.FragmentProfileBinding
import com.lowjungxuan.kotlinexercise.utils.SocketHandler

data class Test(
    var name:String,
    var age:Int
)
class Profile : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
//        SocketHandler.setSocket()
//        SocketHandler.establishConnection()
//        val mSocket = SocketHandler.getSocket()
//        binding.counterBtn.setOnClickListener {
//            val jsonString = Gson().toJson(Test("Low Jung Hong",20))
//            mSocket.emit("data", jsonString)
//        }
//
//        mSocket.on("data") {
//            if (it[0] != null) {
////                val testModel = Gson().fromJson(it[0].toString(), Test::class.java)
//                requireActivity().runOnUiThread {
////                    Log.e("Socket IO Data",testModel.name)
////                    Log.e("Socket IO Data",testModel.age.toString())
//                    binding.countTextView.text = it[0].toString()
//                }
//            }
//        }
        return binding.root
    }
}