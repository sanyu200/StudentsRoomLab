package com.example.studentsroomlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.studentsroomlab.navigation.AppNavGraph
import com.example.studentsroomlab.ui.theme.StudentsRoomLabTheme
import com.example.studentsroomlab.viewmodels.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentViewModel = ViewModelProvider(this)[
            StudentViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            AppNavGraph(studentViewModel)
        }
    }
}

