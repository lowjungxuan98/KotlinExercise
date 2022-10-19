package com.lowjungxuan.kotlinexercise.student.presentation

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lowjungxuan.kotlinexercise.MainActivity
import com.lowjungxuan.kotlinexercise.databinding.FragmentHomeBinding
import com.lowjungxuan.kotlinexercise.student.business.StudentViewModel
import com.lowjungxuan.kotlinexercise.student.business.StudentViewState
import com.lowjungxuan.kotlinexercise.student.data.Student
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StudentFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private val viewModel: StudentViewModel by viewModels()

//    private lateinit var rvAdapter: StudentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mainActivity = (activity as MainActivity)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvStudent.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvStudent.adapter =StudentAdapter(listOf(Student(1,"","",1,null,null)))
        viewModel.viewState.observe(viewLifecycleOwner) {
            updateUI(it)
        }
        viewModel.loadStudentList()
        binding.btnWriteData.setOnClickListener {
            writeData()
            hideKeyboard()
        }
//        binding.btnSearchData.setOnClickListener {
//            searchData()
//        }
//        binding.btnDeleteAll.setOnClickListener {
//            viewModel.deleteAll()
//        }
        binding.btnUpdateData.setOnClickListener {
            updateData()
        }
    }

    private fun updateUI(viewState: StudentViewState) {
        when (viewState) {
            is StudentViewState.Content -> {
                binding.linearLayoutView.isVisible = true
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                binding.rvStudent.adapter = StudentAdapter(viewState.studentList)
            }
            StudentViewState.Error -> {
                binding.linearLayoutView.isVisible = false
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
            }
            StudentViewState.Loading -> {
                binding.linearLayoutView.isVisible = false
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }

    private fun updateData() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val id = binding.etId.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && id.isNotEmpty()) {
            viewModel.updateStudent(firstName, lastName, id.toInt())

            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etId.text.clear()
            mainActivity.showToast("Successfully Updated")
        } else mainActivity.showToast("Please enter the data")
    }

    private fun writeData() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val rollNo = binding.etId.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && rollNo.isNotEmpty()) {
            val student = Student(
                null, firstName, lastName, rollNo.toInt()
            )
            viewModel.insertStudent(student)

            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etId.text.clear()

            mainActivity.showToast("Successfully written")
        } else mainActivity.showToast("Please enter the data")
    }

//    private fun searchData() {
//        val rollNo = binding.etRollNoRead.text.toString()
//        if (rollNo.isNotEmpty()) {
//            viewModel.searchStudent(rollNo.toInt()).observe(viewLifecycleOwner) {
//                if (it != null) {
//                    lifecycleScope.launch {
//                        displayData(it)
//                    }
//                } else {
//                    lifecycleScope.launch {
//                        mainActivity.showToast("No Data Founded")
//                    }
//                }
//            }
//        } else mainActivity.showToast("Please enter the data")
//    }

    private fun hideKeyboard() {
        val view: View? = requireActivity().currentFocus
        if (view != null) {
            val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

//    private suspend fun displayData(student: Student) {
//        withContext(Dispatchers.Main) {
//            binding.tvFirstName.text = student.firstName
//            binding.tvLastName.text = student.lastName
//            binding.tvRollNo.text = student.rollNo.toString()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}