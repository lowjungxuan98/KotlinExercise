package com.lowjungxuan.kotlinexercise.implicit_intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lowjungxuan.kotlinexercise.MainActivity
import com.lowjungxuan.kotlinexercise.databinding.FragmentImplicitIntentBinding


class ImplicitIntentFragment : Fragment() {
    private var _binding: FragmentImplicitIntentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImplicitIntentBinding.inflate(inflater, container, false)
        mainActivity = (activity as MainActivity)
        binding.btnActionView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/lowjungxuan98/KotlinExercise")
            startActivity(intent)
        }
        binding.btnActionDial.setOnClickListener {
            val encodedPhoneNumber = String.format("tel:%s", Uri.encode("0183696935"))
            val number = Uri.parse(encodedPhoneNumber)
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            startActivity(callIntent)
        }
        binding.btnActionSend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val message = "Hello World..."
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(intent)
        }
        return binding.root
    }
}