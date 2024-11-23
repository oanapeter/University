package com.example.lab2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWorkoutScreen(
    workoutViewModel: WorkoutViewModel,
    onWorkoutAdded: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var intensity by remember { mutableStateOf("") }
    var caloriesBurned by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val intensityOptions = listOf("Low", "Medium", "High")

    var nameError by remember { mutableStateOf("") }
    var descriptionError by remember { mutableStateOf("") }
    var durationError by remember { mutableStateOf("") }
    var intensityError by remember { mutableStateOf("") }
    var caloriesBurnedError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add New Workout", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = ""
            },
            label = { Text("Workout Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            isError = nameError.isNotEmpty()
        )
        if (nameError.isNotEmpty()) {
            Text(
                text = nameError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
                descriptionError = ""
            },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            isError = descriptionError.isNotEmpty()
        )
        if (descriptionError.isNotEmpty()) {
            Text(
                text = descriptionError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        OutlinedTextField(
            value = duration,
            onValueChange = {
                duration = it
                durationError = ""
            },
            label = { Text("Duration (minutes)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = durationError.isNotEmpty()
        )
        if (durationError.isNotEmpty()) {
            Text(
                text = durationError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = intensity,
                onValueChange = {
                    intensity = it
                    intensityError = ""
                },
                label = { Text("Intensity") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .menuAnchor(),
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                isError = intensityError.isNotEmpty()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                intensityOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            intensity = option
                            expanded = false
                            intensityError = ""
                        }
                    )
                }
            }
        }
        if (intensityError.isNotEmpty()) {
            Text(
                text = intensityError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        OutlinedTextField(
            value = caloriesBurned,
            onValueChange = {
                caloriesBurned = it
                caloriesBurnedError = ""
            },
            label = { Text("Calories Burned") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = caloriesBurnedError.isNotEmpty()
        )
        if (caloriesBurnedError.isNotEmpty()) {
            Text(
                text = caloriesBurnedError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = {

                var hasError = false
                if (name.isEmpty()) {
                    nameError = "Workout Name cannot be empty."
                    hasError = true
                }
                if (description.isEmpty()) {
                    descriptionError = "Description cannot be empty."
                    hasError = true
                }
                if (duration.toIntOrNull() == null || duration.toInt() <= 0) {
                    durationError = "Duration must be a positive number."
                    hasError = true
                }
                if (intensity.isEmpty()) {
                    intensityError = "Please select an intensity level."
                    hasError = true
                }
                if (caloriesBurned.toIntOrNull() == null || caloriesBurned.toInt() <= 0) {
                    caloriesBurnedError = "Calories Burned must be a positive number."
                    hasError = true
                }
                if (!hasError) {
                    val workout = Workout(
                        id = workoutViewModel.getNextId(),
                        name = name,
                        description = description,
                        duration = duration.toInt(),
                        intensity = intensity,
                        burnedCalories = caloriesBurned.toInt()
                    )
                    workoutViewModel.addWorkout(workout)
                    onWorkoutAdded()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Add Workout")
        }
    }
}
