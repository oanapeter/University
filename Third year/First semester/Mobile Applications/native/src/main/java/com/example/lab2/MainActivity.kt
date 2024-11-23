package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val workoutViewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        setContent {
            Lab2Theme {
                var showAddWorkoutScreen by remember { mutableStateOf(false) }
                var workoutToEdit by remember { mutableStateOf<Workout?>(null) }


                if (showAddWorkoutScreen) {
                    AddWorkoutScreen(
                        workoutViewModel = workoutViewModel,
                        onWorkoutAdded = { showAddWorkoutScreen = false }
                    )
                } else if (workoutToEdit != null) {
                    UpdateWorkoutScreen(
                        workout = workoutToEdit!!,
                        onUpdate = { updatedWorkout ->
                            workoutViewModel.updateWorkout(updatedWorkout)
                            workoutToEdit = null
                        },
                        onCancel = { workoutToEdit = null }
                    )
                } else {
                    MainScreen(workoutViewModel, {
                        showAddWorkoutScreen = true
                    }, { workout ->
                        workoutToEdit = workout
                    })
                }
            }
        }
    }

    @Composable
    fun MainScreen(
        workoutViewModel: WorkoutViewModel,
        onAddWorkoutClicked: () -> Unit,
        onEditWorkout: (Workout) -> Unit
    ) {
        val workouts = workoutViewModel.workouts

        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "MuscleMind",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    WorkoutListScreen(
                        workouts = workouts,
                        onDeleteWorkoutById = { id -> workoutViewModel.removeWorkoutById(id) },
                        onEditWorkout = { workout -> onEditWorkout(workout) }
                    )

                    FloatingActionButton(
                        onClick = { onAddWorkoutClicked() },
                        modifier = Modifier
                            .size(112.dp)
                            .align(Alignment.BottomCenter)
                            .padding(24.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Add Workout"
                        )
                    }
                }
            }
        )
    }






}
