package com.lowjungxuan.kotlinexercise.student.presentation

sealed class StudentViewState {
    object Loading : StudentViewState()
    object Error : StudentViewState()
    data class Content(val studentList: List<StudentCardViewState>) : StudentViewState()
}

