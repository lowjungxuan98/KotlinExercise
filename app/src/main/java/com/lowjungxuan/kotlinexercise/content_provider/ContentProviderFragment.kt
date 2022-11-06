package com.lowjungxuan.kotlinexercise.content_provider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lowjungxuan.kotlinexercise.MainActivity
import com.lowjungxuan.kotlinexercise.databinding.FragmentContentProviderBinding

class ContentProviderFragment : Fragment() {

    private var _binding: FragmentContentProviderBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentContentProviderBinding.inflate(inflater, container, false)
        mainActivity = (activity as MainActivity)
        binding.insertButton.setOnClickListener {
            onClickAddDetails()
        }
        return binding.root
    }
    @SuppressLint("Range", "SetTextI18n")
    private fun onClickAddDetails() {

        // class to add values in the database
        val values = ContentValues()

        // fetching text from user
        values.put(MyContentProvider.name, binding.textName.text.toString())

        // inserting into database through content URI
        mainActivity.contentResolver.insert(MyContentProvider.CONTENT_URI, values)

        // displaying a toast message
        Toast.makeText(requireContext(), "New Record Inserted", Toast.LENGTH_LONG).show()

        // inserting complete table details in this text field
        val resultView = binding.res

        // creating a cursor object of the
        // content URI
        val cursor = mainActivity.contentResolver.query(
            Uri.parse("content://com.kotlin_exercise.user.provider/users"),
            null,
            null,
            null,
            null
        )

        // iteration of the cursor
        // to print whole table
        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append(
                    """${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}""".trimIndent()
                )
                cursor.moveToNext()
            }
            resultView.text = strBuild
        } else {
            resultView.text = "No Records Found"
        }
    }
}