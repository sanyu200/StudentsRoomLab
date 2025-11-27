package com.example.studentsroomlab.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.studentsroomlab.dao.StudentDao
import com.example.studentsroomlab.database.StudentRoomDatabase
import com.example.studentsroomlab.models.Student
import com.example.studentsroomlab.repositories.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>
    init {
        val studentDao = StudentRoomDatabase.StudentRoomDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }
    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        var selectedStudent: Student? = null
        repository.insert(student)
    }
    fun update(student: Student)= viewModelScope.launch(Dispatchers.IO){
      repository.update(student)
    }
    val selectedStudent = mutableStateOf<Student?>(null)
    fun selectStudent(student: Student) {
        selectedStudent.value = student
    }

    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(student)
    }

    fun searchStudents(name: String): LiveData<List<Student>> {
        return repository.searchStudents(name)
    }

}