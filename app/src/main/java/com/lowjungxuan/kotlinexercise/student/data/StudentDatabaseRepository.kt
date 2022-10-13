package com.lowjungxuan.kotlinexercise.student.data

import com.google.gson.Gson
import com.lowjungxuan.kotlinexercise.data.api.StudentEndpoint
import com.lowjungxuan.kotlinexercise.student.business.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class StudentDatabaseRepository @Inject constructor(private val studentDao: StudentDao, private val service: StudentEndpoint) : StudentRepository {
    override suspend fun getStudentList(): List<Student> {
        return withContext(Dispatchers.IO) {
            studentDao.getAll().map {
                Student(it.id, it.firstName, it.lastName, it.rollNo)
            }
            service.findAll().map {
                Student(it.id, it.firstName, it.lastName, it.rollNo)
            }
        }
    }

    override suspend fun insertStudent(student: Student) {
        return withContext(Dispatchers.IO) {
            studentDao.insert(student)
            service.create(
                Gson()
                    .toJson(student)
                    .toRequestBody(
                        "application/json".toMediaTypeOrNull()
                    )
            )
        }
    }

    override suspend fun updateStudent(firstName: String, lastName: String, roll_no: Int) {
        return withContext(Dispatchers.IO) {
            studentDao.update(firstName, lastName, roll_no)
            service.update(
                requestBody = Gson()
                    .toJson(Student(firstName = firstName, lastName = lastName, rollNo = roll_no))
                    .toRequestBody(
                        "application/json".toMediaTypeOrNull()
                    ), roll_no = roll_no
            )
        }
    }

    override suspend fun deleteAll() {
        return withContext(Dispatchers.IO) {
            studentDao.deleteAll()
            service.deleteAll()
        }
    }

    override suspend fun searchStudent(roll_no: Int): Student? {
        return withContext(Dispatchers.IO) {
            studentDao.findByRoll(roll_no)
            service.findOne(roll_no)
        }
    }
}