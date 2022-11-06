package com.lowjungxuan.kotlinexercise.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lowjungxuan.kotlinexercise.MainActivity
import com.lowjungxuan.kotlinexercise.databinding.FragmentServiceBinding

class ServiceFragment : Fragment() {
    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("service fragment life cycle", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("service fragment life cycle", "onCreate")
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentServiceBinding.inflate(inflater, container, false)
        mainActivity = (activity as MainActivity)
        binding.startButton.setOnClickListener {
            mainActivity.startService(Intent(requireContext(), NewService::class.java))
        }
        binding.stopButton.setOnClickListener {
            mainActivity.stopService(Intent(requireContext(), NewService::class.java))
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("service fragment life cycle", "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.e("service fragment life cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("service fragment life cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("service fragment life cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("service fragment life cycle", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("service fragment life cycle", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("service fragment life cycle", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("service fragment life cycle", "onDetach")
    }
}