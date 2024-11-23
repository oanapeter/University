package com.example.lab2

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WorkoutListScreen(
    workouts: List<Workout>,
    onEditWorkout: (Workout) -> Unit,
    onDeleteWorkoutById: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var workoutToDelete by remember { mutableStateOf(0) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(workouts) { workout ->
            WorkoutCard(
                workout = workout,
                onEditWorkout = { onEditWorkout(workout) },
                onDeleteWorkout = { id ->
                    Log.d("WorkoutListScreen", "Workout ID to delete: $id")
                    workoutToDelete = id
                    showDialog = true
                }
            )
        }
    }


    if (showDialog) {
        ConfirmDeleteDialog(
            workoutId = workoutToDelete,
            onConfirmDelete = { id ->
                onDeleteWorkoutById(id)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}


@Composable
fun ConfirmDeleteDialog(
    workoutId: Int,
    onConfirmDelete: (Int) -> Unit,
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirm Deletion") },
        text = { Text("Are you sure you want to delete this workout?") },
        confirmButton = {
            TextButton(onClick = {
                onConfirmDelete(workoutId)
                onDismiss()
            }) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}