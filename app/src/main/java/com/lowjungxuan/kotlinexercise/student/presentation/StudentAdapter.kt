package com.lowjungxuan.kotlinexercise.student.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lowjungxuan.kotlinexercise.databinding.RvStudentListItemBinding
import com.lowjungxuan.kotlinexercise.student.business.StudentCardViewState
import com.lowjungxuan.kotlinexercise.student.data.Student

class StudentAdapter(
    private val studentList: List<Student>,
    val onItemClicked: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = RvStudentListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount() = studentList.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        with(holder) {
            with(studentList[position]) {
                binding.firstName.text = firstName
                binding.lastName.text = lastName
                binding.rollNo.text = rollNo.toString()
                binding.studentCard.setOnClickListener {
                    onItemClicked(studentList[position])
                }
//                binding.topLearnerName.text = name
//                val hours = "$hours learning hours, $country"
//                binding.topLearnerTime.text = hours
//                GlideApp.with(holder.itemView.context)
//                    .load(badgeUrl)
//                    .into(binding.topLearnerImage)
//
//                holder.itemView.setOnClickListener {
//                    Toast.makeText(holder.itemView.context, hours,
//                        Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

    inner class StudentViewHolder(val binding: RvStudentListItemBinding) : RecyclerView.ViewHolder(binding.root)

}