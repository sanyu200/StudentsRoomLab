package com.example.studentsroomlab.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.studentsroomlab.models.Student

@Composable
fun DeleteStudentScreen(
    student: Student,
    onDelete: (Student) -> Unit,
    onCancel: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Voulez-vous vraiment supprimer cet étudiant ?",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nom : ${student.firstName} ${student.lastName}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Téléphone : ${student.phoneNumber}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Email : ${student.email}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Button(
                onClick = {
                    onDelete(student)  // suppression
                    onCancel()         // revenir à l'écran principal
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Supprimer", color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedButton(onClick = onCancel) {
                Text("Annuler")
            }
        }
    }
}
