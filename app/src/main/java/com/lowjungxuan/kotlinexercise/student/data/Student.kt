package com.lowjungxuan.kotlinexercise.student.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int? = null,
    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    var firstName: String? = null,
    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    var lastName: String? = null,
    @ColumnInfo(name = "roll_no")
    @SerializedName("roll_no")
    var rollNo: Int? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("updatedAt")
    var updatedAt: String? = null
)
data class StudentList (
    @SerializedName("data")
    var data: List<Student>? = null,
)