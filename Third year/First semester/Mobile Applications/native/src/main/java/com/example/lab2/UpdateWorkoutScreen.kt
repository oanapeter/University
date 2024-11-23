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
fun UpdateWorkoutScreen(
    workout: Workout,
    onUpdate: (Workout) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(workout.name) }
    var description by remember { mutableStateOf(workout.description) }
    var duration by remember { mutableStateOf(workout.duration.toString()) }
    var intensity by remember { mutableStateOf(workout.intensity) }
    var burnedCalories by remember { mutableStateOf(workout.burnedCalories.toString()) }
    var expanded by remember { mutableStateOf(false) }
    val intensityOptions = listOf("Low", "Medium", "High")

    // Individual error messages
    var nameError by remember { mutableStateOf("") }
    var descriptionError by remember { mutableStateOf("") }
    var durationError by remember { mutableStateOf("") }
    var intensityError by remember { mutableStateOf("") }
    var burnedCaloriesError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Update Workout", style = MaterialTheme.typography.headlineMedium)

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
            Text(nameError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
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
            Text(descriptionError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
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
            Text(durationError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = intensity,
                onValueChange = { intensity = it },
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
            Text(intensityError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        OutlinedTextField(
            value = burnedCalories,
            onValueChange = {
                burnedCalories = it
                burnedCaloriesError = ""
            },
            label = { Text("Calories Burned") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = burnedCaloriesError.isNotEmpty()
        )
        if (burnedCaloriesError.isNotEmpty()) {
            Text(burnedCaloriesError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Row(modifier = Modifier.padding(top = 16.dp)) {
            Button(onClick = {

                nameError = ""
                descriptionError = ""
                durationError = ""
                intensityError = ""
                burnedCaloriesError = ""

                val durationInt = duration.toIntOrNull()
                val caloriesInt = burnedCalories.toIntOrNull()

                when {
                    name.isEmpty() -> nameError = "Workout name cannot be empty."
                    description.isEmpty() -> descriptionError = "Description cannot be empty."
                    duration.isEmpty() || durationInt == null || durationInt < 0 ->
                        durationError = "Duration must be a non-negative number."
                    burnedCalories.isEmpty() || caloriesInt == null || caloriesInt < 0 ->
                        burnedCaloriesError = "Calories burned must be a non-negative number."
                    intensity.isEmpty() -> intensityError = "Please select an intensity level."
                    else -> {
                        val updatedWorkout = workout.copy(
                            name = name,
                            description = description,
                            duration = durationInt,
                            intensity = intensity,
                            burnedCalories = caloriesInt
                        )
                        onUpdate(updatedWorkout)
                    }
                }
            }) {
                Text("Update")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}
