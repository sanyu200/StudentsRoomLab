package com.example.studentsroomlab.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "student_table")
data class Student (
        @PrimaryKey  (autoGenerate = true)
        @ColumnInfo (name= "id")
        val id: Int,
        @ColumnInfo (name ="First_Name")
        val firstName : String,
        @ColumnInfo (name = "Last_Name")
        val lastName : String,
        @ColumnInfo (name = "Phone_number")
        val phoneNumber :String,
        @ColumnInfo (name = "Email")
        val email:String
    )
