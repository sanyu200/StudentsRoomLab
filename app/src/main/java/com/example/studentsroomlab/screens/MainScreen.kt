package com.example.studentsroomlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studentsroomlab.models.Student
import com.example.studentsroomlab.viewmodels.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    students: List<Student>,
    navController: NavController,
    viewModel: StudentViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    val displayedStudents = if (searchQuery.isBlank()) {
        students
    } else {
        viewModel.searchStudents(searchQuery).observeAsState(emptyList()).value
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Liste des étudiants", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D47A1) // Dark Blue
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Ajouter un étudiant",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Rechercher un étudiant") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            if (displayedStudents.isEmpty()) {
                Text(
                    text = "Aucun étudiant trouvé",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else {
                LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                    items(displayedStudents) { student ->
                        StudentItem(
                            student = student,
                            onClick = { navController.navigate("update/${student.id}") },
                            onLongClick = {
                                viewModel.selectStudent(student)
                                navController.navigate("delete")
                            },
                            textColor = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudentItem(
    student: Student,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    textColor: Color
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onClick?.invoke() },
                onLongClick = { onLongClick?.invoke() }
            )
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        Text(
            text = "${student.firstName} ${student.lastName}",
            style = MaterialTheme.typography.titleMedium,
            color = textColor
        )
        Text(
            text = student.phoneNumber,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor.copy(alpha = 0.7f)
        )
    }
}
