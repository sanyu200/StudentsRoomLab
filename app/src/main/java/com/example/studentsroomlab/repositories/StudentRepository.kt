package com.example.studentsroomlab.repositories

import androidx.lifecycle.LiveData
import com.example.studentsroomlab.dao.StudentDao
import com.example.studentsroomlab.models.Student


class StudentRepository( private val studentDao: StudentDao) {
        val allStudents: LiveData<List<Student>> = studentDao.
        getStudents()

        suspend fun insert(student: Student) {
            studentDao.insert(student)
        }
        suspend fun update(student: Student){
            studentDao.updateStudent(student)
        }

        suspend fun delete(student: Student) {
        studentDao.deleteStudent(student)
        }

        fun searchStudents(name: String): LiveData<List<Student>> {
                return studentDao.getStudentsByName("%$name%")
        }

}

