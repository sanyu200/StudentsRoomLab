package com.example.studentsroomlab.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentsroomlab.models.Student
import com.example.studentsroomlab.screens.AddStudentScreen
import com.example.studentsroomlab.screens.DeleteStudentScreen
import com.example.studentsroomlab.screens.MainScreen
import com.example.studentsroomlab.screens.SearchStudentScreen
import com.example.studentsroomlab.screens.UpdateStudentScreen
import com.example.studentsroomlab.viewmodels.StudentViewModel


@Composable
fun AppNavGraph(viewModel: StudentViewModel) {
    val navController = rememberNavController()
    val students by viewModel.allStudents.observeAsState(emptyList())

    NavHost(navController, startDestination = "main") {

        composable("main") {
            MainScreen(
                students = students,
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("add") {
            AddStudentScreen(
                onSave = {
                    viewModel.insert(it)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable("update/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!.toInt()
            val studentToUpdate = viewModel.allStudents.observeAsState(emptyList()).value.find { it.id == id }
            studentToUpdate?.let { student ->
                UpdateStudentScreen(
                    student = student,
                    onUpdate = { updatedStudent ->
                        viewModel.update(updatedStudent)
                        navController.popBackStack()
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
        }

        composable("delete") {
            val studentToDelete = viewModel.selectedStudent.value
            studentToDelete?.let {
                DeleteStudentScreen(
                    student = it,
                    onDelete = { student ->
                        viewModel.delete(student)
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
        composable("search") {
            SearchStudentScreen(viewModel)
        }


    }
}