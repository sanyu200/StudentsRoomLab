package com.example.studentsroomlab.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.studentsroomlab.models.Student


@Dao
interface StudentDao {
    @Query("SELECT * FROM student_table ORDER BY First_Name ASC")
    fun getStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id = :id")
    fun getStudent(id: Int): LiveData<Student?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student_table WHERE First_Name LIKE :name OR Last_Name LIKE :name ORDER BY First_Name ASC")
    fun getStudentsByName(name: String): LiveData<List<Student>>


}