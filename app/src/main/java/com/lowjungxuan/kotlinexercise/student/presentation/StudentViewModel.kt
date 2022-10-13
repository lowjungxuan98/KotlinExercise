package com.lowjungxuan.kotlinexercise.student.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lowjungxuan.kotlinexercise.student.business.StudentRepository
import com.lowjungxuan.kotlinexercise.student.data.Student
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

            val studentList = repository.getStudentList()
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
            Log.e(">>>", studentList.toString())
        }
    }

    fun insertStudent(student: Student) {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(StudentViewState.Loading)
            repository.insertStudent(student)
            loadStudentList()
        }
    }

    fun updateStudent(firstName: String, lastName: String, id: Int){
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(StudentViewState.Loading)
            repository.updateStudent(firstName, lastName, id)
            loadStudentList()
        }
    }

    fun deleteAll(){
        viewModelScope.launch(dispatcher){
            _viewState.postValue(StudentViewState.Loading)
            repository.deleteAll()
            loadStudentList()
        }
    }
    fun searchStudent(id: Int):MutableLiveData<Student?>{
        val result = MutableLiveData<Student?>()
        viewModelScope.launch(dispatcher){
            _viewState.postValue(StudentViewState.Loading)
            result.postValue(repository.searchStudent(id))
            loadStudentList()
        }
        return result
    }
}