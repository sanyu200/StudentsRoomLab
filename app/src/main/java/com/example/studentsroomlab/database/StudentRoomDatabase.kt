package com.example.studentsroomlab.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentsroomlab.models.Student
import com.example.studentsroomlab.dao.StudentDao

abstract class StudentRoomDatabase {
    @Database(entities = [Student::class], version = 1, exportSchema = false)
    abstract class StudentRoomDatabase : RoomDatabase() {

        abstract fun studentDao(): StudentDao

        companion object {

            // Empêche de créer plusieurs instances
            @Volatile
            private var INSTANCE: StudentRoomDatabase? = null

            fun getDatabase(context: Context): StudentRoomDatabase {
                // si une instance existe déjà → on la renvoie
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }

                // sinon on crée une instance
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentRoomDatabase::class.java,
                        "student_database"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}