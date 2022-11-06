package com.lowjungxuan.kotlinexercise.student.business

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lowjungxuan.kotlinexercise.student.data.Student
import com.lowjungxuan.kotlinexercise.student.data.StudentList
import com.lowjungxuan.kotlinexercise.utils.SocketHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: StudentRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _viewState = MutableLiveData<StudentViewState>()
    val viewState: LiveData<StudentViewState>
        get() = _viewState

    fun loadStudentList() {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(StudentViewState.Loading)

            try {
//                val studentList = repository.getStudentList()
//                _viewState.postValue(
//                    StudentViewState.Content(
//                        studentList.map {
//                            StudentCardViewState(
//                                it.id,
//                                it.firstName,
//                                it.lastName,
//                                it.rollNo
//                            )
//                        }
//                    )
//                )
                val mSocket = SocketHandler.getSocket()
                mSocket.emit("student:findAll","initialize")
                mSocket.on("student:findAll") {socketIOResponse->
                    if (socketIOResponse[0] != null) {
                        Log.e("view model socket io", socketIOResponse.toString())
                        val studentList = Gson().fromJson(socketIOResponse[0].toString(), StudentList::class.java).data!!
                        _viewState.postValue(
                            StudentViewState.Content(
                                studentList.map {
                                    StudentCardViewState(
                                        it.id,
                                        it.firstName,
                                        it.lastName,
                                        it.rollNo
                                    )
                                }
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _viewState.postValue(StudentViewState.Error)
                Log.e("Error", e.toString())
            }
        }
    }

    fun insertStudent(student: Student) {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(StudentViewState.Loading)
            repository.insertStudent(student)
            loadStudentList()
        }
    }

    fun updateStudent(firstName: String, lastName: String, id: Int) {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(StudentViewState.Loading)
            repository.updateStudent(firstName, lastName, id)
            loadStudentList()
        }
    }

    fun deleteAll() {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(StudentViewState.Loading)
            repository.deleteAll()
            loadStudentList()
        }
    }

    fun searchStudent(id: Int): MutableLiveData<Student?> {
        val result = MutableLiveData<Student?>()
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(StudentViewState.Loading)
            result.postValue(repository.searchStudent(id))
            loadStudentList()
        }
        return result
    }
}