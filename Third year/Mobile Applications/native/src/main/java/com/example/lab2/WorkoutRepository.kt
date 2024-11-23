package com.example.lab2

import android.util.Log

class WorkoutRepository {
    private val workouts = mutableListOf(
        Workout(1, "Traditional Strength Training", "A weighted workout targeting the lower part of the body.", 35, "High", 350),
        Workout(2, "Stair-Stepper", "A cardio workout that mimics climbing stairs, focusing on improving lower body strength and endurance.", 15, "High", 125)
    )

    fun getWorkouts(): List<Workout> = workouts

    fun addWorkout(workout: Workout) {
        workouts.add(workout)

    }

    fun removeWorkoutById(id: Int) {
        workouts.removeIf { it.id == id }
    }

    fun updateWorkout(updatedWorkout: Workout) {
        val index = workouts.indexOfFirst { it.id == updatedWorkout.id }
        if (index != -1) {
            workouts[index] = updatedWorkout
        }
    }

    fun getNextId(): Int = (workouts.maxOfOrNull { it.id } ?: 0) + 1
}

