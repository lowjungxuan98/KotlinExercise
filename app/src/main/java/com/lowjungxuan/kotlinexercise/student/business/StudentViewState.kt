package com.lowjungxuan.kotlinexercise.student.business

sealed class StudentViewState {
    object Loading : StudentViewState()
    object Error : StudentViewState()
    data class Content(val studentList: List<StudentCardViewState>) : StudentViewState()
}

