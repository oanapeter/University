package com.example.lab2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WorkoutCard(
    workout: Workout,
    onEditWorkout: () -> Unit,
    onDeleteWorkout: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = workout.name,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 25.sp
            )
            Text(
                text = workout.description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp
            )
            Text(
                text = "${workout.duration} minutes",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp
            )
            Text(
                text = "${workout.intensity} Intensity",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp
            )
            Text(
                text = "Calories Burned: ${workout.burnedCalories}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onEditWorkout) {
                    Text("Update", fontSize = 15.sp)
                }
                Button(onClick = { onDeleteWorkout(workout.id) }) {
                    Text("Delete", fontSize = 15.sp)
                }
            }
        }
    }
}
