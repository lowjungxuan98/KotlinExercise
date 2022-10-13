package com.lowjungxuan.kotlinexercise.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lowjungxuan.kotlinexercise.student.data.Student
import com.lowjungxuan.kotlinexercise.student.data.StudentDao

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
}