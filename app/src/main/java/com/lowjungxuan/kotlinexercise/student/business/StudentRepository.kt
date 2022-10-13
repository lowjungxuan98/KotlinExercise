package com.lowjungxuan.kotlinexercise.student.business

import com.lowjungxuan.kotlinexercise.student.data.Student

interface StudentRepository {
    suspend fun getStudentList(): List<Student>
    suspend fun insertStudent(student: Student)
    suspend fun updateStudent(firstName: String, lastName: String, roll_no: Int)
    suspend fun deleteAll()
    suspend fun searchStudent(roll_no: Int):Student?
}