package com.lowjungxuan.kotlinexercise.student.data

import androidx.room.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM student_table")
    suspend fun getAll(): List<Student>

    /* @Query("SELECT * FROM student_table WHERE uid IN (:userIds)")
     fun loadAllByIds(userIds: IntArray): List<Student>*/

    @Query("SELECT * FROM student_table WHERE roll_no LIKE :roll LIMIT 1")
    suspend fun findByRoll(roll: Int): Student?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()

    @Query("UPDATE student_table SET first_name=:firstName,last_name=:lastName WHERE roll_no Like :roll")
    suspend fun update(firstName: String, lastName: String, roll: Int)
}